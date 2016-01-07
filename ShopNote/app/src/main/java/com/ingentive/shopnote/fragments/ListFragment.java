package com.ingentive.shopnote.fragments;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.CurrentListAdapter;
import com.ingentive.shopnote.model.CurrentListModel;

import java.util.List;

//jk
public class ListFragment extends Fragment {

    SwipeMenuListView mListView;
    DatabaseHandler db;
    CurrentListAdapter mAdapter;
    private List<ApplicationInfo> mAppList;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, null);
        mListView = (SwipeMenuListView) rootView.findViewById(R.id.lv_list);
        //mAppList = getPackageManager().getInstalledApplications(0);

        db = new DatabaseHandler(getActivity());
        List<CurrentListModel> currList = db.getCurrList();
        mAdapter = new CurrentListAdapter(getActivity(), currList, R.layout.custom_row_list);
        mListView.setAdapter(mAdapter);
        //Toast.makeText(getActivity(),"size "+currList.size(), Toast.LENGTH_SHORT).show();
        return rootView;
    }
}