package com.ingentive.shopnote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.adapters.ManageSectionAdapter;
import com.ingentive.shopnote.model.ManageSectionModel;
import com.ingentive.shopnote.model.SectionModel;

import java.util.List;

public class ActivityManageSections extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView ivBack;
    AlertDialog.Builder builder1;
    AlertDialog.Builder alertDialogBuilder;
    public static SharedPreferences.Editor editor;
    public static final String MYPREFERENCES = "MyPrefs" ;
    public static final String dbCreated = "dbKey";
    public static final String first_time_dialog = "first_time";
    public static SharedPreferences prefs;
    ManageSectionAdapter mAdapter;
    ListView mListView;
    DatabaseHandler db;
    EditText etAddSection;
    Button btnAddSection;
    List<ManageSectionModel> section;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_section);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_manage_section);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        etAddSection = (EditText) findViewById(R.id.et_add_manage_section);
        btnAddSection = (Button) findViewById(R.id.btn_add_manage_section);
        mListView = (ListView) findViewById(R.id.lv_manage_section);
       ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent mIntent = new Intent(getApplication(),MainActivity.class);
                startActivity(mIntent);
            }
        });



        prefs = ActivityManageSections.this.getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = prefs.getString(first_time_dialog, null);
        if (restoredText == null) {
            showDialog();
            editor = prefs.edit();
            editor.putString(first_time_dialog,"success");
            editor.commit();
        }
        showData();
        //db = new DatabaseHandler(getActivity());
        etAddSection.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!event.isShiftPressed()) {
                        Toast.makeText(getApplication(), "" + etAddSection.getText().toString(), Toast.LENGTH_SHORT).show();
                        if (isSection(etAddSection.getText().toString().trim())){
                            warningDialog();
                        } else{
                            db = new DatabaseHandler(ActivityManageSections.this);
                            SectionModel addSection = new SectionModel();
                            addSection.setSectionOrderNo(99);
                            addSection.setSectionName(etAddSection.getText().toString());
                            addSection.setSectionImage("unknown.png");
                            addSection.setDefaultSection(1);
                            db.addNewSection(addSection);
                            etAddSection.setText("");
                            db = new DatabaseHandler(ActivityManageSections.this);
                            section = db.getSectionData();
                            showData();
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
                    section = db.getSectionData();
                    showData();
                }
            }
        });
    }
    public boolean isSection(String name){
        for(int i=0; i<section.size();i++){
            if(section.get(i).getSectionName().toLowerCase().equals(name.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public void showData(){
        db = new DatabaseHandler(ActivityManageSections.this);
        section = db.getSectionData();
        mAdapter  = new ManageSectionAdapter(ActivityManageSections.this, section, R.layout.custom_row_manage_section);
        mListView.setAdapter(mAdapter);
    }
    public void showDialog(){
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
    public void warningDialog(){
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
