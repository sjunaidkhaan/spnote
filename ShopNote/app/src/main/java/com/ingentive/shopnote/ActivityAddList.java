package com.ingentive.shopnote;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ingentive.shopnote.adapters.DectionaryAdapter;
import com.ingentive.shopnote.adapters.FavoritesHistoryAdapter;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddList extends AppCompatActivity {

    private Toolbar mToolbar;
    private ListView mListView;
    private EditText etSearch;
    private TextView tvPopulerItems;
    private ImageView ivFavorit, ivHistory;
    private AlertDialog.Builder alertDialogBuilder;
    private SharedPreferences.Editor editor;
    private SharedPreferences mPrefs;
    public static String ADD_LIST_PREF = "AddListPref";
    private String list_add_intro_dialog = "list_add_intro";
    private List<DictionaryModel> dicSearchList;
    private List<DictionaryModel> dicCurrentList;
    private List<String> favoritList;
    private List<String> historyList;
    private DectionaryAdapter mAdapter;
    private ArrayList<DictionaryModel> tempFav = new ArrayList<>();
    private ArrayList<DictionaryModel> tempHis = new ArrayList<>();
    private ArrayList<DictionaryModel> tempDictionary = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_listadd);
        ivFavorit = (ImageView) findViewById(R.id.iv_add_list_fav);
        ivHistory = (ImageView) findViewById(R.id.iv_add_list_history);
        tvPopulerItems = (TextView) findViewById(R.id.tv_populer_items);
        //jk
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etSearch = (EditText) findViewById(R.id.etSearch);
        mListView = (ListView) findViewById(R.id.lv_add_list);
        mPrefs = getSharedPreferences(ActivityAddList.ADD_LIST_PREF, MODE_PRIVATE);
        String restoredText = mPrefs.getString(list_add_intro_dialog, null);
        if (restoredText == null) {
            thirdDialog();
            editor = mPrefs.edit();
            editor.putString(list_add_intro_dialog, "success");
            editor.commit();
        }

        favoritList = DatabaseHandler.getInstance(ActivityAddList.this).getFavItem();
        historyList = DatabaseHandler.getInstance(ActivityAddList.this).getHistoryItems();
        dicSearchList = DatabaseHandler.getInstance(ActivityAddList.this).getDicItems();

        for (int j = 0; j < dicSearchList.size(); ++j) {
            for (int k = 0; k < favoritList.size(); ++k) {
                if (dicSearchList.get(j).getItemName().compareTo(favoritList.get(k)) == 0) {
                    dicSearchList.get(j).setFavItem(1);
                }
            }
        }
        for (int j = 0; j < dicSearchList.size(); ++j) {
            for (int k = 0; k < historyList.size(); ++k) {
                if (dicSearchList.get(j).getItemName().compareTo(historyList.get(k)) == 0) {
                    dicSearchList.get(j).setHistoryItem(1);
                }
            }
        }
        //
        showData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = dicCurrentList.get(position).getItemName().toString();
                int order = DatabaseHandler.getInstance(ActivityAddList.this).getMaxOrderNo();
                order++;
                DatabaseHandler.getInstance(ActivityAddList.this).addCurrentList(new CurrentListModel(order, itemName, 0, null, MainActivity.title, 1));
                finish();
            }
        });

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (etSearch.getText().toString().replaceAll(" ", "").length() > 0 && !etSearch.getText().toString().trim().isEmpty()) {
                        List<CurrentListModel> currList = DatabaseHandler.getInstance(ActivityAddList.this).getCurrList();
                        boolean curritemExist = false;
                        for (int i = 0; i < currList.size(); i++) {
                            if (currList.get(i).getItemName().toLowerCase().equals(etSearch.getText().toString().trim().toLowerCase())) {
                                curritemExist = true;
                            }
                        }
                        if (!curritemExist) {
                            int order = DatabaseHandler.getInstance(ActivityAddList.this).getMaxOrderNo();
                            order++;
                            DatabaseHandler.getInstance(ActivityAddList.this).addCurrentList(new CurrentListModel(order, etSearch.getText().toString().trim(), 0, null, MainActivity.title, 1));
                        }
                        List<DictionaryModel> dicList = DatabaseHandler.getInstance(ActivityAddList.this).getDicItems();
                        boolean dicitemExist = false;
                        for (int i = 0; i < dicList.size(); i++) {
                            if (dicList.get(i).getItemName().toLowerCase().equals(etSearch.getText().toString().trim().toLowerCase())) {
                                dicitemExist = true;
                            }
                        }
                        if (!dicitemExist) {
                            DatabaseHandler.getInstance(ActivityAddList.this).addDictionaryNewItem(new DictionaryModel(etSearch.getText().toString().trim(), 99));
                        }
                        ((InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE)).
                                hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                    }
                    finish();
                }
            }
        });
        etSearch.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (etSearch.getText().toString().replaceAll(" ", "").length() > 0 && !etSearch.getText().toString().trim().isEmpty()) {
                            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                    actionId == EditorInfo.IME_ACTION_DONE ||
                                    event.getAction() == KeyEvent.ACTION_DOWN &&
                                            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                ((InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE)).
                                        hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                                List<CurrentListModel> currList = DatabaseHandler.getInstance(ActivityAddList.this).getCurrList();
                                boolean curritemExist = false;
                                for (int i = 0; i < currList.size(); i++) {
                                    if (currList.get(i).getItemName().toLowerCase().equals(etSearch.getText().toString().trim().toLowerCase())) {
                                        curritemExist = true;
                                    }
                                }
                                if (!curritemExist) {
                                    int order = DatabaseHandler.getInstance(ActivityAddList.this).getMaxOrderNo();
                                    order++;
                                    DatabaseHandler.getInstance(ActivityAddList.this).addCurrentList(new CurrentListModel(order, etSearch.getText().toString().trim(), 0, null, MainActivity.title, 1));
                                }
                                List<DictionaryModel> dicList = DatabaseHandler.getInstance(ActivityAddList.this).getDicItems();
                                boolean dicitemExist = false;
                                for (int i = 0; i < dicList.size(); i++) {
                                    if (dicList.get(i).getItemName().toLowerCase().equals(etSearch.getText().toString().trim().toLowerCase())) {
                                        dicitemExist = true;
                                    }
                                }
                                if (!dicitemExist) {
                                    DatabaseHandler.getInstance(ActivityAddList.this).addDictionaryNewItem(new DictionaryModel(etSearch.getText().toString().trim(), 99));
                                }
                                finish();
                            }
                            return true;

                        }
                        return false;
                    }
                });

        etSearch.addTextChangedListener(new TextWatcher() {
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
        DictionaryModel dictionaryModel = new DictionaryModel();
        dicCurrentList = new ArrayList<DictionaryModel>();
        for (int i = 0; i < dicSearchList.size(); i++) {
            if (dicSearchList.get(i).getFavItem() == 1 && dicSearchList.get(i).getHistoryItem() == 1) {
                //jk
                dicCurrentList.add(dicSearchList.get(i));
                //jk
            }
        }
        if (dicCurrentList.size() != 0) {
            tvPopulerItems.setVisibility(View.VISIBLE);
        }

        FavoritesHistoryAdapter mFavHistAdapter = new FavoritesHistoryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
        mListView.setAdapter(mFavHistAdapter);
    }

    private void AlterAdapter() {
        if (etSearch.getText().toString().isEmpty()) {
            dicCurrentList.clear();
            mAdapter.notifyDataSetChanged();
            showData();
        } else {
            tvPopulerItems.setVisibility(View.GONE);
            dicCurrentList.clear();
            tempFav.clear();
            tempHis.clear();
            tempDictionary.clear();
            for (int i = 0; i < dicSearchList.size(); i++) {
                if (dicSearchList.get(i).getItemName().toString().toLowerCase().startsWith(etSearch.getText().toString().trim().toLowerCase())) {
                    //jk
                    if (etSearch.getText().toString().trim().length() == 1) {

                        if (dicSearchList.get(i).getFavItem() == 1) {
                            tempFav.add(dicSearchList.get(i));
                        }
                        if (dicSearchList.get(i).getHistoryItem() == 1 && !(dicSearchList.get(i).getFavItem() == 1)) {
                            tempHis.add(dicSearchList.get(i));
                        }
                    } else if (etSearch.getText().toString().trim().length() == 2) {
                        if (dicSearchList.get(i).getFavItem() == 1) {
                            tempFav.add(dicSearchList.get(i));
                        }
                        if (dicSearchList.get(i).getHistoryItem() == 1 && !(dicSearchList.get(i).getFavItem() == 1)) {
                            tempHis.add(dicSearchList.get(i));
                        }
                        if (!(dicSearchList.get(i).getHistoryItem() == 1) && !(dicSearchList.get(i).getFavItem() == 1)) {
                            tempDictionary.add(dicSearchList.get(i));
                        }
                    } else if (etSearch.getText().toString().trim().length() > 2) {
                        tempDictionary.add(dicSearchList.get(i));
                    }
                }
            }

            for (int i = 0; i < tempFav.size(); ++i) {
                dicCurrentList.add(tempFav.get(i));
            }
            for (int i = 0; i < tempHis.size(); ++i) {
                dicCurrentList.add(tempHis.get(i));
            }
            for (int i = 0; i < tempDictionary.size(); ++i) {
                dicCurrentList.add(tempDictionary.get(i));
            }

            mAdapter = new DectionaryAdapter(ActivityAddList.this, dicCurrentList, R.layout.custom_row_list_add_basic);
            mListView.setAdapter(mAdapter);
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
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            return true;
        }
        if (id == R.id.action_favorites_search) {
            return true;
        }
        if (id == R.id.action_history_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

