package com.ingentive.shopnote;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.adapters.AddListAdapter;
import com.ingentive.shopnote.adapters.DectionaryAdapter;
import com.ingentive.shopnote.adapters.ListAddBasicAdapter;
import com.ingentive.shopnote.model.AddListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.ListAddBasicModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddList extends AppCompatActivity{

    private Toolbar mToolbar;
    AlertDialog.Builder builder1;
    AlertDialog.Builder alertDialogBuilder;
    SharedPreferences.Editor editor;
    public static String ADD_LIST_PREF = "AddListPref" ;
    String list_add_intro_dialog = "list_add_intro";
    SharedPreferences mPrefs;
    AutoCompleteTextView mAutoComptv;
    DatabaseHandler db;
    ImageView ivFav;
    ListView mListView;
    EditText etSerch;
    private ArrayList<String> historyItems;
    private ArrayList<String> favoritItems;
    private ArrayList<String> searchItems;
    List<String> dicSearchList ;
    List<DictionaryModel> dictionaryModel;
   // List<AddListModel> addList;
    AddListAdapter mAdapter;
    List<String> addList;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_listadd);
        ivFav = (ImageView) findViewById(R.id.iv_fav);
        //mAutoComptv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTV);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etSerch = (EditText) findViewById(R.id.etSearch);
        mListView = (ListView) findViewById(R.id.lv_add_list);


        /*db = new DatabaseHandler(getApplication());
        addList = db.getFavItem();
        mAdapter = new AddListAdapter(ActivityAddList.this, addList, R.layout.custom_row_list_add_basic);
        mListView.setAdapter(mAdapter);*/

        db = new DatabaseHandler(getApplication());
        addList = db.getFavItem();

        db = new DatabaseHandler(getApplication());
        dictionaryModel = db.getItems();

        Toast.makeText(getApplication(),""+dictionaryModel.get(0).getItemName(),Toast.LENGTH_LONG).show();
        adapter = new ArrayAdapter<String>(ActivityAddList.this,android.R.layout.simple_list_item_1,addList);
        mListView.setAdapter(adapter);

        //get item names from dictionary table
        db = new DatabaseHandler(getApplication());
        dicSearchList = new ArrayList<String>();
        dicSearchList = db.getDicItems();
        //AlterAdapter();
        etSerch.addTextChangedListener(new TextWatcher() {
            // As the user types in the search field, the list is
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //AlterAdapter();
            }

            // Not used for this program
            @Override
            public void afterTextChanged(Editable arg0) {
                AlterAdapter();

            }

            // Not uses for this program
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });
        //Toast.makeText(getApplication(),""+dicSearchList,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplication(),""+data,Toast.LENGTH_LONG).show();
        //Log.d("Items Names ",""+data);
        /*db = new DatabaseHandler(getApplication());
        List<ListAddBasicModel> basicList = db.getItemName();
        ListAddBasicAdapter lda = new ListAddBasicAdapter(ActivityAddList.this, R.layout.custom_row_list_add_basic,basicList);
        mAutoComptv.setAdapter(lda);*/

        mPrefs= getSharedPreferences(ActivityAddList.ADD_LIST_PREF, MODE_PRIVATE);
        String restoredText = mPrefs.getString(list_add_intro_dialog, null);
        if (restoredText == null) {
            thirdDialog();
           editor = mPrefs.edit();
            editor.putString(list_add_intro_dialog,"success");
            editor.commit();
        }
    }

    // Filters list of contacts based on user search criteria. If no information is filled in, contact list will be blank.
    private void AlterAdapter() {
        if (etSerch.getText().toString().isEmpty()) {
            addList.clear();
            adapter.notifyDataSetChanged();
        }
        else {
            addList.clear();
            for (int i = 0; i < dicSearchList.size(); i++) {
                if (dicSearchList.get(i).toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())) {
                    addList.add(dicSearchList.get(i).toString());
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void thirdDialog(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Supermarket items that match your text " +
                "will appear as you type. You can also new items which will be " +
                "saved and matched in the future.");

        alertDialogBuilder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

