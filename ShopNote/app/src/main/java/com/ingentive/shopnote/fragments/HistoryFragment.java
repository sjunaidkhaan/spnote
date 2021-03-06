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

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.HistoryCustomAdapter;
import com.ingentive.shopnote.model.HistoryChildModel;
import com.ingentive.shopnote.model.HistoryParentModel;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {

    private SharedPreferences.Editor editor;
    private final String MYPREFERENCES = "MyPrefs";
    private final String first_time_dialog = "first_time";
    private SharedPreferences prefs;
    private ExpandableListView mExpHistoryList;
    private HistoryCustomAdapter mAdapter;
    private ArrayList<String> arrayPar = new ArrayList<String>();
    private HistoryParentModel parentModel = new HistoryParentModel();
    private List<HistoryParentModel> historyList = new ArrayList<HistoryParentModel>();
    private List<HistoryChildModel> hisChiList;
    private List<HistoryParentModel> hisParList;

    public HistoryFragment() {
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
        hisParList = DatabaseHandler.getInstance(getActivity()).getHisPar();
        for (int i = 0; i < hisParList.size(); i++) {
            if (!arrayPar.contains(hisParList.get(i).getHisPaDatePurchased().toString())) {
                arrayPar.add(hisParList.get(i).getHisPaDatePurchased().toString());
                hisChiList = DatabaseHandler.getInstance(getActivity()).getHisChil(hisParList.get(i).getHisPaDatePurchased().toString());
                parentModel = new HistoryParentModel();
                parentModel.setHisPaDatePurchased(hisParList.get(i).getHisPaDatePurchased().toString());
                parentModel.setArrayChildren(hisChiList);
                historyList.add(parentModel);
            }
        }
        mAdapter = new HistoryCustomAdapter(getActivity(), historyList);
        mExpHistoryList.setAdapter(mAdapter);
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
