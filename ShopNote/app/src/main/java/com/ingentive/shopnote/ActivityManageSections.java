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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private AlertDialog.Builder alertDialogBuilder;
    private SharedPreferences.Editor editor;
    private String MANAGESECTION = "ManageSectionMyPrefs";
    private String manage_section_dialog = "first_time";
    private SharedPreferences prefs;
    private ManageSectionAdapter mAdapter;
    private DynamicListView mListView;
    private EditText etAddSection;
    private Button btnAddSection;
    private List<ManageSectionModel> sectionData;
    private SimpleSwipeUndoAdapter swipeUndoAdapter;
    private ManageSectionModel model;
    public static String itemNameUseInAdapter = "";

    public static String sectionName = "";
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
        etAddSection = (EditText) findViewById(R.id.et_add_manage_section);
        btnAddSection = (Button) findViewById(R.id.btn_add_manage_section);
        mListView = (DynamicListView) findViewById(R.id.lv_manage_section);
        mListView.enableDragAndDrop();
        sectionData = DatabaseHandler.getInstance(ActivityManageSections.this).getSectionData();
        mAdapter = new ManageSectionAdapter(ActivityManageSections.this, sectionData, R.layout.custom_row_manage_section);

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
                List<ManageSectionModel> secList = DatabaseHandler.getInstance(ActivityManageSections.this).getSectionData();
                for (int i = 0; i < sectionData.size() && i < secList.size(); i++) {
                    model = new ManageSectionModel();
                    model.setSectionName(sectionData.get(i).getSectionName());
                    model.setOrderNo(secList.get(i).getOrderNo());
                    model.setManageSectionId(sectionData.get(i).getManageSectionId());
                    DatabaseHandler.getInstance(ActivityManageSections.this).updateOrderNo(model);
                }
                mAdapter.notifyDataSetChanged();
            }

        });

        prefs = ActivityManageSections.this.getSharedPreferences(MANAGESECTION, Context.MODE_PRIVATE);
        String restoredText = prefs.getString(manage_section_dialog, null);
        if (restoredText == null) {
            showDialog();
            editor = prefs.edit();
            editor.putString(manage_section_dialog, "success");
            editor.commit();
        }
        showData();

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("item_name");
        itemNameUseInAdapter = itemName;
        for (int i = 0; i < sectionData.size(); i++) {
            boolean exist = DatabaseHandler.getInstance(ActivityManageSections.this).isExist(sectionData.get(i).getManageSectionId(), itemName);
            if (exist) {
                sectionName = sectionData.get(i).getSectionName();
            }
        }
        etAddSection.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (etAddSection.getText().toString().replaceAll(" ", "").length() > 0 && !etAddSection.getText().toString().isEmpty()) {
                        ((InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE)).
                                hideSoftInputFromWindow(etAddSection.getWindowToken(), 0);
                        if (etAddSection.getText().toString().replaceAll(" ", "").length() > 0) {
                            if (isSection(etAddSection.getText().toString().trim())) {
                                warningDialog();
                            } else {
                                SectionModel addSection = new SectionModel();
                                addSection.setSectionOrderNo(99);
                                String str = etAddSection.getText().toString();
                                str = str.trim();
                                String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                                addSection.setSectionName(cap);
                                addSection.setSectionImage(str.substring(0, 1).toLowerCase() + ".png");
                                addSection.setDefaultSection(1);
                                DatabaseHandler.getInstance(ActivityManageSections.this).addNewSection(addSection);
                                etAddSection.setText("");
                                sectionData = DatabaseHandler.getInstance(ActivityManageSections.this).getSectionData();
                                showData();
                            }
                        }
                    }
                    return true;
                }
                return false;
            }
        });
        btnAddSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAddSection.getText().toString().replaceAll(" ", "").length() > 0 && !etAddSection.getText().toString().isEmpty()) {
                    ((InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE)).
                            hideSoftInputFromWindow(etAddSection.getWindowToken(), 0);
                    if (isSection(etAddSection.getText().toString().trim())) {
                        warningDialog();
                    } else {
                        SectionModel addSection = new SectionModel();
                        addSection.setSectionOrderNo(99);
                        String str = etAddSection.getText().toString();
                        str = str.trim();
                        String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                        addSection.setSectionName(cap);
                        addSection.setSectionImage(str.substring(0, 1).toLowerCase() + ".png");
                        addSection.setDefaultSection(1);
                        DatabaseHandler.getInstance(ActivityManageSections.this).addNewSection(addSection);
                        etAddSection.setText("");
                        sectionData = DatabaseHandler.getInstance(ActivityManageSections.this).getSectionData();
                        showData();
                    }
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
        sectionData = DatabaseHandler.getInstance(ActivityManageSections.this).getSectionData();
        mAdapter = new ManageSectionAdapter(ActivityManageSections.this, sectionData, R.layout.custom_row_manage_section);
        swipeUndoAdapter = new SimpleSwipeUndoAdapter(mAdapter, ActivityManageSections.this,
                new OnDismissCallback() {
                    @Override
                    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {

                        }
                    }
                }
        );
        swipeUndoAdapter.setAbsListView(mListView);
        mListView.setAdapter(swipeUndoAdapter);
        mListView.enableSimpleSwipeUndo();
    }

    public void showDialog() {
        //AlertDialog.Builder
        alertDialogBuilder = new AlertDialog.Builder(ActivityManageSections.this)
                .setMessage("Reassign item on your list to new sections or" +
                        " add and delete sections. Rearrange these sections in the order that you shop.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

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
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
