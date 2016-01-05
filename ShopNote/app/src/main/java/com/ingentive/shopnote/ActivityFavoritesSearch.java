package com.ingentive.shopnote;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.adapters.DectionaryAdapter;
import com.ingentive.shopnote.adapters.FavoritesListAdapter;
import com.ingentive.shopnote.fragments.FavoritsFragment;
import com.ingentive.shopnote.fragments.FeedbackFragment;
import com.ingentive.shopnote.fragments.ManageSectionsFragment;
import com.ingentive.shopnote.fragments.MyFirstNoteFragment;
import com.ingentive.shopnote.fragments.ShareListFragment;
import com.ingentive.shopnote.model.AddListModel;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.FavoritListModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityFavoritesSearch extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private EditText etFavSerch;
    private ImageView ivFavorite, ivAdd;
    private FavoritesListAdapter mAdapter;
    public DatabaseHandler db;
    List<FavoritListModel> favList;
    List<FavoritListModel> searchItem;
    public static boolean fromFavoriteBackHit  =false;


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
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //
        db = new DatabaseHandler(getApplication());
        favList = db.getFavList();
        //Toast.makeText(getApplication(),"size "+favList.get(0).getItemName().toString(),Toast.LENGTH_LONG).show();
        //mAdapter = new FavoritesListAdapter(ActivityFavoritesSearch.this, favList, R.layout.custom_row_favorites);
       // mListView.setAdapter(mAdapter);


        showData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String item =  parent.getSelectedItem().toString();
                //String itemName = favList.get(position).getItemName().toString();
                // db = new DatabaseHandler(getApplication());
                //db.addCurrentList(new CurrentListModel(1, itemName, 0, null, "My Firts Shopnote", 1));
                //Toast.makeText(getApplicationContext(), "itemName  " + itemName, Toast.LENGTH_LONG).show();
            }
        });

        /*etSerch.setOnEditorActionListener(
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
                                Toast.makeText(ActivityFavoritesSearch.this, "in Editor " + etSerch.getText().toString(), Toast.LENGTH_SHORT).show();
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                });*/

        etFavSerch.addTextChangedListener(new TextWatcher() {
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
        // db = new DatabaseHandler(getApplication());
        searchItem = new ArrayList<FavoritListModel>();
        mAdapter = new FavoritesListAdapter(ActivityFavoritesSearch.this, searchItem, R.layout.custom_row_favorites);
        mListView.setAdapter(mAdapter);
    }

    // Filters list of contacts based on user search criteria. If no information is filled in, contact list will be blank.
    private void AlterAdapter() {
        if (etFavSerch.getText().toString().isEmpty()) {
            mAdapter.notifyDataSetChanged();
            showData();
        } else {
            db = new DatabaseHandler(getApplication());
            favList = db.getFavList();
            FavoritListModel modleObj = new FavoritListModel();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_add, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
//            android.support.v4.app.Fragment fragment  = new FavoritsFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.container_body, fragment);
//            fragmentTransaction.commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
//        android.support.v4.app.Fragment fragment  = new FavoritsFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.container_body, fragment);
//        fragmentTransaction.commit();.
       // fromFavoriteBackHit = true;
        //finish();
    }
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        Log.d("onKeyDown ","keyCode "+keyCode);
        Log.d("onKeyDown ","KeyEvent "+event);
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            // Take care of calling this method on earlier versions of
            // the platform where it doesn't exist.
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }*/
}

