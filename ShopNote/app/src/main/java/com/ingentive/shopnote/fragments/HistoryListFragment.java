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
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.HistoryListAdapter;
import com.ingentive.shopnote.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;


public class HistoryListFragment extends Fragment {

    public static SharedPreferences.Editor editor;
    public static final String MYPREFERENCES = "MyPrefs";
    public static final String dbCreated = "dbKey";
    public static final String first_time_dialog = "first_time";
    public static SharedPreferences prefs;
    // private ListView mListView;
    private ExpandableListView mExpHistoryList;
    private HistoryListAdapter mAdapter;
    public DatabaseHandler db;
    List<HistoryModel> historyList;
    List<HistoryModel> searchItem;
    ListView mListView;

    public HistoryListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        mListView = (ListView) rootView.findViewById(R.id.history_list_view);
        searchItem = new ArrayList<HistoryModel>();
        db = new DatabaseHandler(getActivity());
        historyList = db.getHistory();
        HistoryModel modleObj = new HistoryModel();
       // Toast.makeText(getActivity(),"size "+historyList.size(),Toast.LENGTH_LONG).show();
        for (int i = 0; i < historyList.size(); i++) {
            //Toast.makeText(getApplication(),historyList.get(i).getItemName().toString(),Toast.LENGTH_LONG).show();
            //if (historyList.get(i).getItemName().toString().toLowerCase().startsWith(etHistorySerch.getText().toString().toLowerCase())) {
                boolean exists = false;
                for ( int j = 0; j < searchItem.size(); ++j ){
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
        //}
        mAdapter = new HistoryListAdapter(getActivity(), searchItem, R.layout.custom_row_history);
        mListView.setAdapter(mAdapter);
        //mAdapter.notifyDataSetChanged();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        //inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_history_search).setVisible(true);
        menu.findItem(R.id.action_favorites_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
