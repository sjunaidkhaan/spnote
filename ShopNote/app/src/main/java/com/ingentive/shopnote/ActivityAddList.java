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
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
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
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.ListAddBasicModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ActivityAddList extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private EditText etSerch;
    private TextView tvPopulerItems;
    private ImageView ivFavorit, ivHistory;
    private AlertDialog.Builder builder1;
    private AlertDialog.Builder alertDialogBuilder;
    private SharedPreferences.Editor editor;
    private SharedPreferences mPrefs;
    public static String ADD_LIST_PREF = "AddListPref";
    private String list_add_intro_dialog = "list_add_intro";
    private ArrayList<String> historyItems;
    private ArrayList<String> favoritItems;
    private ArrayList<String> searchItems;
    private List<String> dicSearchlist;
    private List<DictionaryModel> dicSearchList;
    private List<DictionaryModel> dicCurrentList;
    // List<AddListModel> addList;
    private List<String> favoritList;
    private List<String> historyList;
    private DectionaryAdapter mAdapter;
    private List<AddListModel> addListModels;
    private ArrayList<DictionaryModel> tempFav = new ArrayList<>();
    private ArrayList<DictionaryModel> tempHis = new ArrayList<>();
    private ArrayList<DictionaryModel> tempDictionary = new ArrayList<>();
    public DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_listadd);
        ivFavorit = (ImageView) findViewById(R.id.iv_add_list_fav);
        ivHistory = (ImageView) findViewById(R.id.iv_add_list_history);
        tvPopulerItems = (TextView) findViewById(R.id.tv_populer_items);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etSerch = (EditText) findViewById(R.id.etSearch);
        mListView = (ListView) findViewById(R.id.lv_add_list);
        mPrefs = getSharedPreferences(ActivityAddList.ADD_LIST_PREF, MODE_PRIVATE);
        String restoredText = mPrefs.getString(list_add_intro_dialog, null);
        if (restoredText == null) {
            thirdDialog();
            editor = mPrefs.edit();
            editor.putString(list_add_intro_dialog, "success");
            editor.commit();
        }
        //db = new DatabaseHandler(getApplication());
        //addList = db.getFavItem();


        db = new DatabaseHandler(getApplication());
        favoritList = db.getFavItem();
        historyList = db.getHistoryItems();
        dicSearchList = db.getDicItems();
        DictionaryModel model = new DictionaryModel();

        //jk
        for ( int j = 0; j < dicSearchList.size(); ++j ){
            for ( int k = 0; k < favoritList.size(); ++k){
                if ( dicSearchList.get(j).getItemName().compareTo(favoritList.get(k))==0 ){
                    dicSearchList.get(j).setFavItem(1);
                }
            }
        }
        for ( int j = 0; j < dicSearchList.size(); ++j ){
            for ( int k = 0; k < historyList.size(); ++k){
                if ( dicSearchList.get(j).getItemName().compareTo(historyList.get(k))==0 ){
                    dicSearchList.get(j).setHistoryItem(1);
                }
            }
        }
        //
        showData();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //String item =  parent.getSelectedItem().toString();
                String itemName =  dicCurrentList.get(position).getItemName().toString();
                db = new DatabaseHandler(getApplication());
                db.addCurrentList(new CurrentListModel(1, itemName, 0, null, "My Firts Shopnote", 1));
                Toast.makeText(getApplicationContext(), "itemName  "+itemName, Toast.LENGTH_LONG).show();
            }
        });


        etSerch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    db = new DatabaseHandler(getApplication());
                    db.addCurrentList(new CurrentListModel(1, etSerch.getText().toString(), 0, null, "My Firts Shopnote", 1));
                    db = new DatabaseHandler(getApplication());
                    db.addDictionaryNewItem(new DictionaryModel(etSerch.getText().toString(), 11));
                    Toast.makeText(ActivityAddList.this, "in Focus " + etSerch.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        etSerch.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                // the user is done typing.
                                db = new DatabaseHandler(getApplication());
                                db.addCurrentList(new CurrentListModel(1, etSerch.getText().toString(), 0, null, "My Firts Shopnote", 1));
                                db = new DatabaseHandler(getApplication());
                                db.addDictionaryNewItem(new DictionaryModel(etSerch.getText().toString(), 11));
                                Toast.makeText(ActivityAddList.this, "in Editor " + etSerch.getText().toString(), Toast.LENGTH_SHORT).show();
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });

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

    }

    public void showData() {
        DictionaryModel dictionaryModel = new DictionaryModel();
        dicCurrentList = new ArrayList<DictionaryModel>();
        for (int i = 0; i < dicSearchList.size(); i++) {
            if (dicSearchList.get(i).getFavItem() == 1 && dicSearchList.get(i).getHistoryItem() == 1) {
                //jk
                dicCurrentList.add(dicSearchList.get(i));
                //jk
            }
        }
        mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
        mListView.setAdapter(mAdapter);
        //tvPopulerItems.setVisibility(View.VISIBLE);
    }

    // Filters list of contacts based on user search criteria. If no information is filled in, contact list will be blank.
    private void AlterAdapter() {
        if (etSerch.getText().toString().isEmpty()) {
            dicCurrentList.clear();
            mAdapter.notifyDataSetChanged();
            showData();
        } else {
            tvPopulerItems.setVisibility(View.GONE);
            DictionaryModel dictionaryModel = new DictionaryModel();
            dicCurrentList.clear();
            tempFav.clear();
            tempHis.clear();
            tempDictionary.clear();
            for (int i = 0; i < dicSearchList.size(); i++) {
                if (dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())) {
                    //jk

                    if ( etSerch.getText().toString().length() == 1){

                        if ( dicSearchList.get(i).getFavItem()==1 ){
                            tempFav.add(dicSearchList.get(i));
                            //dicCurrentList.add(dicSearchList.get(i));
                        }
                        if ( dicSearchList.get(i).getHistoryItem()== 1 && !(dicSearchList.get(i).getFavItem()==1)) {
                            tempHis.add(dicSearchList.get(i));
                            //dicCurrentList.add(dicSearchList.get(i));
                        }
                    }else if ( etSerch.getText().toString().length() == 2) {
                        if ( dicSearchList.get(i).getFavItem()==1 ){
                            tempFav.add(dicSearchList.get(i));
                            //dicCurrentList.add(dicSearchList.get(i));
                        }
                        if ( dicSearchList.get(i).getHistoryItem()== 1 && !(dicSearchList.get(i).getFavItem()==1)) {
                            tempHis.add(dicSearchList.get(i));
                            //dicCurrentList.add(dicSearchList.get(i));
                        }
                        if (!(dicSearchList.get(i).getHistoryItem()== 1) && !(dicSearchList.get(i).getFavItem()==1)){
                            tempDictionary.add(dicSearchList.get(i));
                        }
                    }
                }
            }
            //jk

            for ( int i = 0; i < tempFav.size(); ++i ){
                dicCurrentList.add(tempFav.get(i));
            }
            for ( int i = 0; i < tempHis.size(); ++i ){
                dicCurrentList.add(tempHis.get(i));
            }
            for ( int i = 0; i < tempDictionary.size(); ++i ){
                dicCurrentList.add(tempDictionary.get(i));
            }

            mAdapter.notifyDataSetChanged();
            mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
            mListView.setAdapter(mAdapter);
            //
        }
    }

    public void thirdDialog() {
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
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

