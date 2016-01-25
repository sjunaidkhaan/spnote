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

import com.ingentive.shopnote.adapters.FavoritesListAdapter;
import com.ingentive.shopnote.model.FavoritListModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityFavoritesSearch extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private EditText etFavSerch;
    private ImageView ivFavorite, ivAdd;
    private FavoritesListAdapter mAdapter;
    private List<FavoritListModel> favList;
    private List<FavoritListModel> searchItem;
    public static boolean fromFavoriteBackHit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fromFavoriteBackHit = false;

        setContentView(R.layout.activity_favorites_search);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_favorites);
        ivFavorite = (ImageView) findViewById(R.id.iv_fav);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        etFavSerch = (EditText) findViewById(R.id.et_favorites_search);
        mListView = (ListView) findViewById(R.id.lv_favorites_search);
        mToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        favList = DatabaseHandler.getInstance(ActivityFavoritesSearch.this).getFavList();

        showData();
        etFavSerch.addTextChangedListener(new TextWatcher() {
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
        searchItem = new ArrayList<FavoritListModel>();
        mAdapter = new FavoritesListAdapter(ActivityFavoritesSearch.this, searchItem, R.layout.custom_row_favorites);
        mListView.setAdapter(mAdapter);
    }

    private void AlterAdapter() {
        if (etFavSerch.getText().toString().isEmpty()) {
            mAdapter.notifyDataSetChanged();
            showData();
        } else {
            searchItem = new ArrayList<FavoritListModel>();
            favList = DatabaseHandler.getInstance(ActivityFavoritesSearch.this).getFavList();
            for (int i = 0; i < favList.size(); i++) {
                if (favList.get(i).getItemName().toString().toLowerCase().startsWith(etFavSerch.getText().toString().toLowerCase())) {
                    searchItem.add(favList.get(i));
                }
            }
            mAdapter.notifyDataSetChanged();
            mAdapter = new FavoritesListAdapter(ActivityFavoritesSearch.this, searchItem, R.layout.custom_row_favorites);
            mListView.setAdapter(mAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_favorites_search).setVisible(true);
        menu.findItem(R.id.action_history_search).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorites_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

