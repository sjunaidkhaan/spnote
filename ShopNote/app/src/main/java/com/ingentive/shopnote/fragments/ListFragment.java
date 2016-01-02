package com.ingentive.shopnote.fragments;

import android.app.ActionBar;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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


public class ListFragment extends Fragment{

    ListView mListView;
    DatabaseHandler db;

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
        View rootView =  inflater.inflate(R.layout.fragment_list, null);
        mListView = (ListView) rootView.findViewById(R.id.lv_list);

        db = new DatabaseHandler(getActivity());
       List<CurrentListModel> currList = db.getCurrList();
        mListView.setAdapter(new CurrentListAdapter(getActivity(), currList, R.layout.custom_row_list));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(), "Item: " , Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
