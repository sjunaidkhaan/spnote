package com.ingentive.shopnote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.ingentive.shopnote.adapters.HistorySearchAdapter;
import com.ingentive.shopnote.model.HistoryModel;
import com.ingentive.shopnote.model.HistoryModelJk;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistorySearch extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private EditText etHistorySerch;
    private ImageView ivBack;
    private HistorySearchAdapter mAdapter;
    public DatabaseHandler db;
    List<HistoryModel> historyList;
    List<HistoryModel> searchItem;
    List<HistoryModelJk> historyListJk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_search);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_history);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.back);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etHistorySerch = (EditText)findViewById(R.id.et_history_search);
        mListView = (ListView) findViewById(R.id.lv_history_search);
        //db = new DatabaseHandler(getApplication());
        //historyList = db.getHistory();
        //Toast.makeText(getApplication(),historyList.get(0).getItemName().toString(),Toast.LENGTH_LONG).show();
        showData();

        /*mAdapter = new HistoryListAdapter(ActivityHistorySearch.this, historyList, R.layout.custom_row_history);
        mListView.setAdapter(mAdapter);*/
        etHistorySerch.addTextChangedListener(new TextWatcher() {
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
    }
    public void showData() {
        // db = new DatabaseHandler(getApplication());
        searchItem = new ArrayList<HistoryModel>();
        mAdapter = new HistorySearchAdapter(ActivityHistorySearch.this, searchItem, R.layout.custom_row_history);
        mListView.setAdapter(mAdapter);
    }

    private void AlterAdapter() {
       // Toast.makeText(getApplication(), "input  " + etHistorySerch.getText().toString(), Toast.LENGTH_LONG).show();
        if (etHistorySerch.getText().toString().isEmpty()) {
            mAdapter.notifyDataSetChanged();
            showData();
        } else {
            searchItem = new ArrayList<HistoryModel>();
            db = new DatabaseHandler(getApplication());
            historyList = db.getHistory();
            HistoryModel modleObj = new HistoryModel();
            for (int i = 0; i < historyList.size(); i++) {
                //Toast.makeText(getApplication(),historyList.get(i).getItemName().toString(),Toast.LENGTH_LONG).show();
                if (historyList.get(i).getItemName().toString().toLowerCase().startsWith(etHistorySerch.getText().toString().toLowerCase())) {
                    boolean exists = false;
                    for ( int j = 0; j < searchItem.size()-1; ++j ){
                        if ( searchItem.get(j).getDatePurchased().compareTo(historyList.get(i).getDatePurchased()) == 0){
                            exists = true;
                        }
                    }
                    if ( !exists ){
                        HistoryModel hTemp = new HistoryModel();
                        hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                        //hTemp.setQuantity(historyList.get(i).getQuantity());
                        hTemp.setItemName(historyList.get(i).getItemName());
                        hTemp.setHistoryId(historyList.get(i).getHistoryId());
                        hTemp.setIsDate(true);
                        searchItem.add(hTemp);

                        hTemp = new HistoryModel();
                        hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                        //hTemp.setQuantity(historyList.get(i).getQuantity());
                        hTemp.setItemName(historyList.get(i).getItemName());
                        hTemp.setHistoryId(historyList.get(i).getHistoryId());
                        hTemp.setIsDate(false);
                        searchItem.add(hTemp);
                    }else{
                        HistoryModel hTemp = new HistoryModel();
                        hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                        //hTemp.setQuantity(historyList.get(i).getQuantity());
                        hTemp.setItemName(historyList.get(i).getItemName());
                        hTemp.setHistoryId(historyList.get(i).getHistoryId());
                        hTemp.setIsDate(false);
                        searchItem.add(hTemp);
                    }
                }
            }
            mAdapter.notifyDataSetChanged();
            mAdapter = new HistorySearchAdapter(ActivityHistorySearch.this, searchItem, R.layout.custom_row_history);
            mListView.setAdapter(mAdapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_favorites_search).setVisible(false);
        menu.findItem(R.id.action_history_search).setVisible(true);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_history_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}