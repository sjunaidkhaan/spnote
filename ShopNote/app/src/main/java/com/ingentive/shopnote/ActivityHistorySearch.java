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
import android.widget.ListView;

import com.ingentive.shopnote.adapters.HistorySearchAdapter;
import com.ingentive.shopnote.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityHistorySearch extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private EditText etHistorySerch;
    private HistorySearchAdapter mAdapter;
    private List<HistoryModel> historyList;
    private List<HistoryModel> searchItem;


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

        etHistorySerch = (EditText) findViewById(R.id.et_history_search);
        mListView = (ListView) findViewById(R.id.lv_history_search);
        showData();

        etHistorySerch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //AlterAdapter();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                AlterAdapter();
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void showData() {
        searchItem = new ArrayList<HistoryModel>();
        mAdapter = new HistorySearchAdapter(ActivityHistorySearch.this, searchItem, R.layout.custom_row_history);
        mListView.setAdapter(mAdapter);
    }

    private void AlterAdapter() {
        if (etHistorySerch.getText().toString().isEmpty()) {
            mAdapter.notifyDataSetChanged();
            showData();
        } else {
            searchItem = new ArrayList<HistoryModel>();
            historyList = DatabaseHandler.getInstance(ActivityHistorySearch.this).getHistory();
            for (int i = 0; i < historyList.size(); i++) {
                if (historyList.get(i).getItemName().toString().toLowerCase().startsWith(etHistorySerch.getText().toString().toLowerCase())) {
                    boolean exists = false;
                    for (int j = 0; j < searchItem.size() - 1; ++j) {
                        if (searchItem.get(j).getDatePurchased().compareTo(historyList.get(i).getDatePurchased()) == 0) {
                            exists = true;
                        }
                    }
                    if (!exists) {
                        HistoryModel hTemp = new HistoryModel();
                        hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                        hTemp.setItemName(historyList.get(i).getItemName());
                        hTemp.setHistoryId(historyList.get(i).getHistoryId());
                        hTemp.setIsDate(true);
                        searchItem.add(hTemp);

                        hTemp = new HistoryModel();
                        hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                        hTemp.setItemName(historyList.get(i).getItemName());
                        hTemp.setHistoryId(historyList.get(i).getHistoryId());
                        hTemp.setIsDate(false);
                        searchItem.add(hTemp);
                    } else {
                        HistoryModel hTemp = new HistoryModel();
                        hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
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