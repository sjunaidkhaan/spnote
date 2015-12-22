package com.ingentive.pro.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ContactFragment extends Fragment {

    DatabaseHandler db;
    ListView mListView;
    ContactAdapter contactAdapter;
    private TextView tvId,tvPhone;


    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        View rootView =  inflater.inflate(R.layout.fragment_contact, null);
        mListView = (ListView) rootView.findViewById(R.id.listView_contact);
        db = new DatabaseHandler(getActivity());
        List<Contact> contacts = db.getAllContacts();
        mListView.setAdapter(new ContactAdapter(getActivity(), contacts, R.layout.custom_row_contact));

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
