package com.ingentive.shopnote;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.fragments.MyFirstNoteFragment;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.SectionModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private SharedPreferences.Editor editor;
    private final String MyPREFERENCES = "MyPrefs";
    private final String dbCreated = "dbKey";
    private SharedPreferences prefs;
    private TextView tvToolbarTitle;
    private EditText edToolbarTitle;
    public static String title = "My First Shopnote";
    public boolean dbCreate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
            String restoredText = prefs.getString(dbCreated, null);
            if (restoredText == null) {
                editor = prefs.edit();
                editor.putString(dbCreated, "success");
                editor.commit();
                BackgroundTask task = new BackgroundTask(MainActivity.this);
                task.execute();
            } else {
                dbCreate = true;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        edToolbarTitle = (EditText) findViewById(R.id.edittext_toolbar_title);
        tvToolbarTitle = (TextView) findViewById(R.id.textview_title_toolbar);
        tvToolbarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvToolbarTitle.setVisibility(View.GONE);
                edToolbarTitle.setVisibility(View.VISIBLE);
                edToolbarTitle.requestFocus();
                ((InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE))
                        .showSoftInput(edToolbarTitle, InputMethodManager.SHOW_FORCED);
                String listName = DatabaseHandler.getInstance(MainActivity.this).getListName();
                edToolbarTitle.setText(listName);
            }
        });

        edToolbarTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ((InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(edToolbarTitle.getWindowToken(), 0);
                    if (edToolbarTitle.getText().toString().replaceAll(" ", "").length() > 0) {
                        CurrentListModel curr = new CurrentListModel();
                        curr.setListName(edToolbarTitle.getText().toString());
                        DatabaseHandler.getInstance(MainActivity.this).updateListName(curr);
                        tvToolbarTitle.setText(edToolbarTitle.getText().toString());
                        title = edToolbarTitle.getText().toString();
                        tvToolbarTitle.setVisibility(View.VISIBLE);
                        edToolbarTitle.setVisibility(View.GONE);
                    }
                }
            }
        });

        edToolbarTitle.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                ((InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE))
                                        .hideSoftInputFromWindow(edToolbarTitle.getWindowToken(), 0);
                                if (edToolbarTitle.getText().toString().replaceAll(" ", "").length() > 0) {
                                    CurrentListModel curr = new CurrentListModel();
                                    curr.setListName(edToolbarTitle.getText().toString());
                                    DatabaseHandler.getInstance(MainActivity.this).updateListName(curr);
                                    tvToolbarTitle.setText(edToolbarTitle.getText().toString());
                                    title = edToolbarTitle.getText().toString();
                                    tvToolbarTitle.setVisibility(View.VISIBLE);
                                    edToolbarTitle.setVisibility(View.GONE);
                                }
                                return true;
                            }
                        }
                        return false;
                    }
                });

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerFragment.setDrawerListener(this);
        if (dbCreate == true) {
            displayView(3);
            //MyFirstNoteFragment fragment = new MyFirstNoteFragment()
        }
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Intent intent;
        android.support.v4.app.Fragment fragment = null;
        //displayView(3);
        title = DatabaseHandler.getInstance(MainActivity.this).getListName();
        if (title == null || title.isEmpty())
            title = "My First Shopnote";
        switch (position) {
            case 0:
                intent = new Intent(getApplication(), ActivityShareList.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getApplication(), ActivityManageSections.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getApplication(), ActivityFeedBack.class);
                startActivity(intent);
                break;
            case 3:
                fragment = new MyFirstNoteFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            tvToolbarTitle.setText(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent mIntent;
        switch (id) {
            case R.id.action_add:
                mIntent = new Intent(getApplication(), ActivityAddList.class);
                startActivity(mIntent);
                break;
            case R.id.action_favorites_search:
                mIntent = new Intent(getApplication(), ActivityFavoritesSearch.class);
                startActivity(mIntent);
                break;
            case R.id.action_history_search:
                mIntent = new Intent(getApplication(), ActivityHistorySearch.class);
                startActivity(mIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private class BackgroundTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;

        public BackgroundTask(MainActivity activity) {
            progressDialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Logging data. Please wait.");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
                displayView(3);
                dbCreate = true;
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            StringBuffer sb = new StringBuffer();
            BufferedReader reader = null;
            String str = "";
            String[] separated;

            try {
                str = "";
                List<DictionaryModel> dicList = new ArrayList<DictionaryModel>();
                DictionaryModel dicModel;
                reader = new BufferedReader(new InputStreamReader(getAssets().open("dictionary")));
                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    dicModel = new DictionaryModel();
                    dicModel.setSectionId(Integer.parseInt(separated[0]));
                    dicModel.setItemName(separated[1].toString());
                    dicList.add(dicModel);
                }
                DatabaseHandler.getInstance(MainActivity.this).addDictionary(dicList);
                str = "";
                List<SectionModel> secList = new ArrayList<SectionModel>();
                SectionModel secModel;
                reader = new BufferedReader(new InputStreamReader(getAssets().open("sectionorder")));

                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    secModel = new SectionModel();
                    secModel.setSectionOrderNo(Integer.parseInt(separated[0]));
                    secModel.setSectionName(separated[1]);
                    secModel.setSectionImage(separated[2]);
                    secModel.setDefaultSection(Integer.parseInt(separated[3]));
                    secList.add(secModel);
                }
                DatabaseHandler.getInstance(MainActivity.this).addSection(secList);
                dbCreate = true;

            } catch (IOException e) {
                //e.printStackTrace();
                Toast.makeText(getApplicationContext(),
                        "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                try {
                    reader.close();
                    //db.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}