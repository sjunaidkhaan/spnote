package com.ingentive.shopnote.fragments;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.NameAdapter;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.CurrentListAdapter;
import com.ingentive.shopnote.adapters.ListAdapter;
import com.ingentive.shopnote.adapters.NavigationDrawerAdapter;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.ListModel;
import com.ingentive.shopnote.model.NavDrawerItemModel;

import java.util.ArrayList;
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