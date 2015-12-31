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

public class ActivityAddList extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private EditText etSerch;
    private ImageView ivFavorit, ivHistory;
    private AlertDialog.Builder builder1;
    private AlertDialog.Builder alertDialogBuilder;
    private SharedPreferences.Editor editor;
    private SharedPreferences mPrefs;
    private DatabaseHandler db;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_listadd);
        ivFavorit = (ImageView) findViewById(R.id.iv_add_list_fav);
        ivHistory = (ImageView) findViewById(R.id.iv_add_list_history);

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


//        for (int i = 0; i < dicSearchList.size(); i++) {
//            if (favoritList.contains(dicSearchList.get(i).getItemName().toString())) {
//                dicSearchList.get(i).setFavItem(1);
//            }
//            if (historyList.contains(dicSearchList.get(i).getItemName().toString())) {
//                dicSearchList.get(i).setHistoryItem(1);
//            }
//        }
       /* for(int i= 0; i<dicSearchList.size();i++){
            if(dicSearchList.get(i).getFavItem()==1 && dicSearchList.get(i).getHistoryItem()==1){
                model.setItemName(dicSearchList.get(i).getItemName());
                model.setFavIcon(dicSearchList.get(i).getFavIcon());
                model.setHistoryIcon(dicSearchList.get(i).getHistoryIcon());
                dicCurrentList.add(model);
            }
        }
        mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
        mListView.setAdapter(mAdapter);*/
        showData();

        //adapter = new ArrayAdapter<String>(ActivityAddList.this, android.R.layout.simple_list_item_1, favoritList);
        //mListView.setAdapter(adapter);
        //mAdapter = new AddListAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
        //mListView.setAdapter(mAdapter);
        //get item names from dictionary table
        //db = new DatabaseHandler(getApplication());
        //dicSearchlist = new ArrayList<String>();
        //dicSearchList = db.getDicItems();
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
    }

    // Filters list of contacts based on user search criteria. If no information is filled in, contact list will be blank.
    private void AlterAdapter() {
        if (etSerch.getText().toString().isEmpty()) {
            dicCurrentList.clear();
            mAdapter.notifyDataSetChanged();
            showData();
        } else {
            DictionaryModel dictionaryModel = new DictionaryModel();
              dicCurrentList.clear();
                    for (int i = 0; i < dicSearchList.size(); i++) {
                        if (dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())) {
//                            if (favoritList.contains(dicSearchList.get(i).getItemName())) {
//                                Toast.makeText(getApplication(), "favorit  " + dicSearchList.get(i).getItemName(), Toast.LENGTH_LONG).show();
//                                dictionaryModel.setItemName(dicSearchList.get(i).getItemName());
//                                dictionaryModel.setFavIcon(dicSearchList.get(i).getFavIcon());
//                                dicCurrentList.add(dictionaryModel);
//                            }
//                            if (historyList.contains(dicSearchList.get(i).getItemName()) && !dicCurrentList.contains(dicSearchList.get(i).getItemName())) {
//                                dictionaryModel.setItemName(dicSearchList.get(i).getItemName());
//                                dictionaryModel.setHistoryIcon(dicSearchList.get(i).getHistoryIcon());
//                                dicCurrentList.add(dictionaryModel);
//                            }
//                            if (!dicCurrentList.contains(dicSearchList.get(i).getItemName())) {
//                                dictionaryModel = new DictionaryModel();
//                                dictionaryModel.setItemName(dicSearchList.get(i).getItemName());
//                                dicCurrentList.add(dictionaryModel);
//                            }

                            //jk
                            dicCurrentList.add(dicSearchList.get(i));
                            //jk
                        }
                    }
            //jk
            mAdapter.notifyDataSetChanged();
            mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
            mListView.setAdapter(mAdapter);
            //
                }



        /*else {
            DictionaryModel dictionaryModel = new DictionaryModel();
            if (etSerch.getText().toString().length() == 1) {
                Toast.makeText(getApplication(), "length " + etSerch.length(), Toast.LENGTH_LONG).show();
                dicCurrentList.clear();
                for (int i = 0; i < dicSearchList.size(); i++) {
                    if (dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())) {
                        if (favoritList.contains(dicSearchList.get(i).getItemName())&&!dicCurrentList.contains(dicSearchList.get(i).getItemName())) {
                            Toast.makeText(getApplication(), "favoritList "+ dicSearchList.get(i).getItemName(), Toast.LENGTH_LONG).show();
                            dictionaryModel.setFavItem(1);
                            dictionaryModel.setHistoryItem(0);
                            dictionaryModel.setItemName(dicSearchList.get(i).getItemName());
                            dictionaryModel.setFavIcon(dicSearchList.get(i).getFavIcon());
                            dicCurrentList.add(dictionaryModel);
                            mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
                            mListView.setAdapter(mAdapter);
                        }
                        if (historyList.contains(dicSearchList.get(i).getItemName()) && dicSearchList.get(i).getFavItem()==0) {
                            //Toast.makeText(getApplication(), "historyList " + dicCurrentList.get(i).getItemName(), Toast.LENGTH_LONG).show();
                            dictionaryModel.setHistoryItem(1);
                            // new DictionaryModel(dicSearchList.get(i).getItemName(), dicSearchList.get(i).getFavIcon(), dicSearchList.get(i).getHistoryIcon());
                            dictionaryModel.setItemName(dicSearchList.get(i).getItemName());
                            dictionaryModel.setHistoryIcon(dicSearchList.get(i).getHistoryIcon());
                            dicCurrentList.add(dictionaryModel);
                            mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
                            mListView.setAdapter(mAdapter);
                            // Toast.makeText(getApplication(), "historyList " + dicCurrentList.get(i).getItemName(), Toast.LENGTH_LONG).show();
                        }

                        //Toast.makeText(getApplication(), "favoritList " + dicCurrentList.get(i).getItemName(), Toast.LENGTH_LONG).show();
                    }
                    /* if (dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())&&
                            favoritList.get(i).toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())) {
                        dictionaryModel.setFavItem(1);
                        dictionaryModel.setHistoryItem(0);
                        //ivHistory.setVisibility(View.GONE);
                        //new DictionaryModel(favoritList.get(i), dicSearchList.get(i).getFavIcon(), dicSearchList.get(i).getHistoryIcon());
                        dictionaryModel.setItemName(favoritList.get(i));
                        dictionaryModel.setFavIcon(dicSearchList.get(i).getFavIcon());
                        dicCurrentList.add(dictionaryModel);
                        Toast.makeText(getApplication(), "favoritList " + dicCurrentList.get(i).getItemName(), Toast.LENGTH_LONG).show();
                    }*/
        //if (historyList.get(i).toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())) {

        // }
        //}
           /* if (etSerch.getText().toString().length() == 2) {
                Toast.makeText(getApplication(), "length " + etSerch.length(), Toast.LENGTH_LONG).show();
                for (int i = 0; i < dicSearchList.size(); i++) {
                    if (favoritList.contains(dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase()))) {
                        dicSearchList.get(i).setFavItem(1);
                        ivHistory.setVisibility(View.GONE);
                        dicCurrentList.add(dicSearchList.get(i));
                    }
                    if (historyList.contains(dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())
                            && !dicCurrentList.contains(dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())))) {
                        dicSearchList.get(i).setHistoryItem(1);
                        ivHistory.setVisibility(View.VISIBLE);
                        ivFavorit.setVisibility(View.GONE);
                        dicCurrentList.add(dicSearchList.get(i));
                    }
                }
                mAdapter.notifyDataSetChanged();
                mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
                mListView.setAdapter(mAdapter);
            }*/

                /*if (dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSerch.getText().toString().toLowerCase())) {
                    dicCurrentList.add(dicSearchList.get(i));
                }*/
        // mAdapter.notifyDataSetChanged();
        //mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
        // mListView.setAdapter(mAdapter);
        //}
            /*
             tvToolbarTitle.setVisibility(View.VISIBLE);
                                edToolbarTitle.setVisibility(View.GONE);
            if (historyList.contains(dicSearchList.get(i).getItemName()) && !dicCurrentList.contains(dicSearchList.get(i).getItemName())) {
                        dicSearchList.get(i).setHistoryItem(1);
                        dicCurrentList.add(dicSearchList.get(i));
                    }
             */
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
        return super.onOptionsItemSelected(item);
    }
}

