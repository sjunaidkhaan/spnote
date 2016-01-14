package com.ingentive.shopnote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.adapters.ManageSectionAdapter;
import com.ingentive.shopnote.model.ManageSectionModel;
import com.ingentive.shopnote.model.SectionModel;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.SimpleSwipeUndoAdapter;

import java.util.List;

public class ActivityManageSections extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView ivBack;
    AlertDialog.Builder builder1;
    AlertDialog.Builder alertDialogBuilder;
    public static SharedPreferences.Editor editor;
    public static final String MYPREFERENCES = "MyPrefs";
    public static final String dbCreated = "dbKey";
    public static final String first_time_dialog = "first_time";
    public static SharedPreferences prefs;
    ManageSectionAdapter mAdapter;
    DynamicListView mListView;
    DatabaseHandler db;
    EditText etAddSection;
    Button btnAddSection;
    List<ManageSectionModel> sectionData;
    SimpleSwipeUndoAdapter swipeUndoAdapter;
    ManageSectionModel model;
    public static String itemNameUseInAdapter="";
    public View row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_section);


        mToolbar = (Toolbar) findViewById(R.id.toolbar_manage_section);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //ivBack = (ImageView) findViewById(R.id.back);
        etAddSection = (EditText) findViewById(R.id.et_add_manage_section);
        btnAddSection = (Button) findViewById(R.id.btn_add_manage_section);
        mListView = (DynamicListView) findViewById(R.id.lv_manage_section);
        mListView.enableDragAndDrop();
        db = new DatabaseHandler(ActivityManageSections.this);
        sectionData = db.getSectionData();
        mAdapter = new ManageSectionAdapter(ActivityManageSections.this, sectionData, R.layout.custom_row_manage_section);

        //mListView.setAdapter(mAdapter);
//        Intent intent = getIntent();
//        String itemName  = intent.getStringExtra("item_name");
//        Toast.makeText(getApplication(), "item name " + itemName, Toast.LENGTH_LONG).show();
//        for(int i=0; i<sectionData.size();i++){
//            db = new DatabaseHandler(ActivityManageSections.this);
//            boolean exist = db.isExist(sectionData.get(i).getManageSectionId(),itemName);
//            if(exist){
//                //row = mListView.getChildAt(i - mListView .getFirstVisiblePosition());
//                //row.setBackgroundColor(Color.BLACK);
//                //mListView.getChildAt(i).setBackgroundColor(Color.BLACK);
//                //mListView.getCount();
//
//                final int firstListItemPosition = mListView.getFirstVisiblePosition();
//                final int lastListItemPosition = firstListItemPosition + mListView.getChildCount() - 1;
//
//                if (i < firstListItemPosition || i > lastListItemPosition ) {
//                    mListView.getAdapter().getView(i, null, mListView);
//                    Toast.makeText(getApplication(), "if child "+mListView.getAdapter().getView(i, null, mListView), Toast.LENGTH_LONG).show();
//                } else {
//                    final int childIndex = i - firstListItemPosition;
//                    mListView.getChildAt(childIndex);
//                    Toast.makeText(getApplication(), "else child "+mListView.getChildAt(childIndex), Toast.LENGTH_LONG).show();
//                }
//            }
//        }


//        mListView.enableSwipeToDismiss(new OnDismissCallback() {
//            @Override
//            public void onDismiss(ViewGroup listView, int[] reverseSortedPositions) {
//                for (int position : reverseSortedPositions) {
//                    section.remove(position);
//                }
//            }
//        });


        mListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(final AdapterView<?> parent, final View view,
                                                   final int position, final long id) {
                        mListView.startDragging(position - mListView.getHeaderViewsCount());
                        return true;
                    }
                }
        );


        mListView.setOnItemMovedListener(new OnItemMovedListener() {
            @Override
            public void onItemMoved(int originalPosition, int newPosition) {
                List<ManageSectionModel> secList = db.getSectionData();
                for(int i=0; i<sectionData.size() && i<secList.size();i++){
                    model =new ManageSectionModel();
                    model.setSectionName(sectionData.get(i).getSectionName());
                    model.setOrderNo(secList.get(i).getOrderNo());
                    model.setManageSectionId(sectionData.get(i).getManageSectionId());
                    db = new DatabaseHandler(ActivityManageSections.this);
                    db.updateOrderNo(model);
                }
                mAdapter.notifyDataSetChanged();
                //Toast.makeText(ActivityManageSections.this, "Item moved from :" + originalPosition + "To " + newPosition, Toast.LENGTH_LONG).show();
            }

        });

//       ivBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent mIntent = new Intent(getApplication(),MainActivity.class);
//                startActivity(mIntent);
//            }
//        });


        prefs = ActivityManageSections.this.getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = prefs.getString(first_time_dialog, null);
        if (restoredText == null) {
            showDialog();
            editor = prefs.edit();
            editor.putString(first_time_dialog, "success");
            editor.commit();
        }
        showData();


        Intent intent = getIntent();
        String itemName  = intent.getStringExtra("item_name");
        itemNameUseInAdapter = itemName;
        //Toast.makeText(getApplication(), "item name " + itemName, Toast.LENGTH_LONG).show();
        for(int i=0; i<sectionData.size();i++){
            db = new DatabaseHandler(ActivityManageSections.this);
            boolean exist = db.isExist(sectionData.get(i).getManageSectionId(),itemName);
            if(exist){
                //row = mListView.getChildAt(i - mListView .getFirstVisiblePosition());
                //row.setBackgroundColor(Color.BLACK);
                //mListView.getChildAt(i).setBackgroundColor(Color.BLACK);
                //mListView.getCount();

                Toast.makeText(getApplication(), itemName+" already assigned", Toast.LENGTH_LONG).show();
//                final int firstListItemPosition = mListView.getFirstVisiblePosition();
//                final int lastListItemPosition = firstListItemPosition + mListView.getChildCount() - 1;
//
//                if (i < firstListItemPosition || i > lastListItemPosition ) {
//                    mListView.getAdapter().getView(i, null, mListView).setBackgroundColor(Color.BLACK);
//                    Toast.makeText(getApplication(), "if child "+mListView.getAdapter().getView(i, null, mListView), Toast.LENGTH_LONG).show();
//                } else {
//                    final int childIndex = i - firstListItemPosition;
//                    mListView.getChildAt(childIndex);
//                    Toast.makeText(getApplication(), "else child "+mListView.getChildAt(childIndex), Toast.LENGTH_LONG).show();
//                }
            }
        }

        //db = new DatabaseHandler(getActivity());
        etAddSection.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!event.isShiftPressed()) {
                        if (etAddSection.getText().toString().replaceAll(" ", "").length() > 0) {
                            if (isSection(etAddSection.getText().toString().trim())) {
                                warningDialog();
                            } else {
                                db = new DatabaseHandler(ActivityManageSections.this);
                                SectionModel addSection = new SectionModel();
                                addSection.setSectionOrderNo(99);
                                addSection.setSectionName(etAddSection.getText().toString());
                                addSection.setSectionImage("unknown.png");
                                addSection.setDefaultSection(1);
                                db.addNewSection(addSection);
                                etAddSection.setText("");
                                db = new DatabaseHandler(ActivityManageSections.this);
                                sectionData = db.getSectionData();
                                showData();
                            }
                        }
                        // the user is done typing.
                        //db = new DatabaseHandler(getApplication());
                        // db.addCurrentList(new CurrentListModel(1, etSerch.getText().toString(), 0, null, "My Firts Shopnote", 1));
                        return true;
                    }
                }
                return false;
            }
        });
        btnAddSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityManageSections.this, "" + etAddSection.getText().toString(), Toast.LENGTH_SHORT).show();
                if (isSection(etAddSection.getText().toString().trim())) {
                    warningDialog();
                } else {
                    db = new DatabaseHandler(ActivityManageSections.this);
                    SectionModel addSection = new SectionModel();
                    addSection.setSectionOrderNo(99);
                    addSection.setSectionName(etAddSection.getText().toString());
                    addSection.setSectionImage("unknown.png");
                    addSection.setDefaultSection(1);
                    db.addNewSection(addSection);
                    etAddSection.setText("");
                    db = new DatabaseHandler(ActivityManageSections.this);
                    sectionData = db.getSectionData();
                    showData();
                }
            }
        });
    }

    public boolean isSection(String name) {
        for (int i = 0; i < sectionData.size(); i++) {
            if (sectionData.get(i).getSectionName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void showData() {
        db = new DatabaseHandler(ActivityManageSections.this);
        sectionData = db.getSectionData();
        mAdapter = new ManageSectionAdapter(ActivityManageSections.this, sectionData, R.layout.custom_row_manage_section);
        swipeUndoAdapter = new SimpleSwipeUndoAdapter(mAdapter, ActivityManageSections.this,
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Toast.makeText(ActivityManageSections.this, "activity", Toast.LENGTH_LONG).show();
                            //section.remove(position);

                        }
                    }
                }
        );
        swipeUndoAdapter.setAbsListView(mListView);
        mListView.setAdapter(swipeUndoAdapter);
        mListView.enableSimpleSwipeUndo();



        //mListView.setAdapter(mAdapter);

    }

    public void showDialog() {
        //AlertDialog.Builder
        alertDialogBuilder = new AlertDialog.Builder(ActivityManageSections.this)
                .setMessage("Reassign item on your list to new sections or" +
                        " add and delete sections. Rearrange these sections in the order that you shop.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void warningDialog() {
        //AlertDialog.Builder
        alertDialogBuilder = new AlertDialog.Builder(ActivityManageSections.this)
                .setTitle("Section already exists.")
                .setMessage("New Section must have a unique name.")
                .setIcon(R.drawable.error)
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
