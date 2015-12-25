package com.ingentive.shopnote;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ingentive.shopnote.fragments.MyFirstNoteFragment;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.FavoritListModel;
import com.ingentive.shopnote.model.HistoryModel;
import com.ingentive.shopnote.model.InventoryModel;
import com.ingentive.shopnote.model.ScreenTextModel;
import com.ingentive.shopnote.model.SectionModel;
import com.ingentive.shopnote.model.SettingModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    public DatabaseHandler db;
    public static String LOG_MSG = "MAINACTIVITY";
    String fileName = "emptyfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.app_bar);

      try {
          PlayWithRawFiles(fileName);
          /*fileName = "dictionary";
          PlayWithRawFiles(fileName);
          fileName = "sectionorder";
          PlayWithRawFiles(fileName);
          fileName = "currentlist";
          PlayWithRawFiles(fileName);
          fileName = "favoritlist";
          PlayWithRawFiles(fileName);
          fileName = "inventrylist";
          PlayWithRawFiles(fileName);
          fileName = "settinglist";
          PlayWithRawFiles(fileName);
          fileName = "historylist";
          PlayWithRawFiles(fileName);
          fileName = "screentextlist";
          PlayWithRawFiles(fileName);*/
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        db = new DatabaseHandler(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(2);
        MyFirstNoteFragment fragment = new MyFirstNoteFragment();

    }

    public void PlayWithRawFiles(String filname) throws IOException {
        String str="";
        String[] separated;
        //StringBuffer buf = new StringBuffer();
        InputStream file_Name = this.getAssets().open(filname);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file_Name));
        if (file_Name!=null) {
            if(filname.equals("dictionary")){
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated= str.split(",");
                    db.addDictionary(new DictionaryModel(separated[1], Integer.parseInt(separated[0])));
                }
                db.close();
            }
            else  if(filname.equals("sectionorder")){
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated= str.split(",");
                    db.addSection(new SectionModel(Integer.parseInt(separated[0]),separated[1],
                            "ic_tab_call",Integer.parseInt(separated[3])));
                }
                db.close();
            }
            else  if(filname.equals("currentlist")) {
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    db.addCurrentList(new CurrentListModel(Integer.parseInt(separated[0]), separated[1],
                            Integer.parseInt(separated[2]), separated[3], Integer.parseInt(separated[4])));
                }
                db.close();
            }
            else  if(filname.equals("favoritlist")) {
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    db.addFavorit(new FavoritListModel(separated[0]));
                }
                db.close();
            }
            else  if(filname.equals("inventrylist")) {
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    db.addInventry(new InventoryModel(Integer.parseInt(separated[0]), separated[1]));
                }
                db.close();
            }
            else  if(filname.equals("settinglist")) {
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    db.addSetting(new SettingModel(separated[0], Integer.parseInt(separated[1]),
                            Integer.parseInt(separated[2]), Integer.parseInt(separated[3]),
                            Integer.parseInt(separated[4]), Integer.parseInt(separated[5]),
                            Integer.parseInt(separated[6]), Integer.parseInt(separated[7]),
                            Integer.parseInt(separated[8]), Integer.parseInt(separated[9]),
                            Integer.parseInt(separated[10]), Integer.parseInt(separated[11])));
                }
                db.close();
            }
            else  if(filname.equals("historylist")) {
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    db.addHistory(new HistoryModel(separated[0], separated[1],separated[2]));
                }
                db.close();
            }
            else  if(filname.equals("screentextlist")) {
                db = new DatabaseHandler(this);
                while ((str = reader.readLine()) != null) {
                    separated = str.split(",");
                    db.addScreenText(new ScreenTextModel(separated[0], separated[1]));
                }
                db.close();
            }
            else  if(filname.equals("emptyfile")){
                Toast.makeText(getApplicationContext(),"empty file",Toast.LENGTH_LONG).show();
            }
        }
        file_Name.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        android.support.v4.app.Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new ContactFragment();
                title = getString(R.string.title_contact);
                break;
            case 1:
                fragment = new NameFragment();
                title = getString(R.string.title_name);
                break;
            case 2:
                fragment = new MyFirstNoteFragment();
                title = getString(R.string.nav_item_firstnote);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}