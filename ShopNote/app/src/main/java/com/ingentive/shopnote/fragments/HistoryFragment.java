package com.ingentive.shopnote.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.FavoritesListAdapter;
import com.ingentive.shopnote.adapters.HistoryListAdapter;
import com.ingentive.shopnote.model.FavoritListModel;
import com.ingentive.shopnote.model.HistoryModel;

import java.util.List;


public class HistoryFragment extends Fragment {

    public static SharedPreferences.Editor editor;
    public static final String MYPREFERENCES = "MyPrefs";
    public static final String dbCreated = "dbKey";
    public static final String first_time_dialog = "first_time";
    public static SharedPreferences prefs;
    private ListView mListView;
    DatabaseHandler db;
    HistoryListAdapter mAdapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        //inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, null);
        prefs = this.getActivity().getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = prefs.getString(first_time_dialog, null);
        if (restoredText == null) {
            showDialog();
            editor = prefs.edit();
            editor.putString(first_time_dialog, "success");
            editor.commit();
        }

        mListView = (ListView) rootView.findViewById(R.id.lv_history);
        db = new DatabaseHandler(getActivity());
        List<HistoryModel> favList = db.getHistory();
        mAdapter = new HistoryListAdapter(getActivity(), favList, R.layout.custom_row_history);
        mListView.setAdapter(mAdapter);
        return rootView;
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setMessage("Your history stores your completed purchased for your records." +
                        " Any items that you mark off your list while shopping will be " +
                        "stored in history.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
