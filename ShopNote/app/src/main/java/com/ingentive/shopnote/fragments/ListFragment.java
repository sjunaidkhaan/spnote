package com.ingentive.shopnote.fragments;

import android.app.ActionBar;
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
import com.ingentive.shopnote.adapters.ListAdapter;
import com.ingentive.shopnote.adapters.NavigationDrawerAdapter;
import com.ingentive.shopnote.model.ListModel;
import com.ingentive.shopnote.model.NavDrawerItemModel;

import java.util.ArrayList;
import java.util.List;


public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView mListView;
    private TextView tvItemName;
    private ImageView ivOption,ivFavorit,ivSection;


    public static int[] icons = {
            R.drawable.grab_grabbed,
            R.drawable.favorite_unselected,
            R.drawable.tomato
    };

    public static List<ListModel> getData() {
        List<ListModel> data = new ArrayList<>();
       /*int[] icons = {
                R.drawable.grab_grabbed,
                R.drawable.favorite_unselected,
               R.drawable.tomato
        };*/
        String[] itemNames = {
                "Onions",
                "Peppers",
                "8oz Chicken Broth",
                "Mac and Cheese",
                "Kleenex",
                "Bananas"
        };
        for (int i = 0; i < itemNames.length; i++) {
            ListModel listModel = new ListModel();
            listModel.setIconOption(icons[0]);
            listModel.setItemName(itemNames[i]);
            listModel.setIconFavorit(icons[1]);
            listModel.setIconSection(icons[2]);
            data.add(listModel);
        }
        return data;
    }

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
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_list, null);
        mListView = (ListView) rootView.findViewById(R.id.lv_list);
        mListView.setAdapter(new ListAdapter(getActivity(), getData(), R.layout.custom_row_list));
        mListView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}
