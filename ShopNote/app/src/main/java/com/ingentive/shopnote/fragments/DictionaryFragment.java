package com.ingentive.shopnote.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ingentive.shopnote.ContactAdapter;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.DectionaryAdapter;


public class DictionaryFragment extends Fragment {

    DatabaseHandler db;
    ListView mListView;
    DectionaryAdapter dictionaryAdapter;

    public DictionaryFragment() {
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

        View rootView =  inflater.inflate(R.layout.fragment_dictionary, null);
        mListView = (ListView) rootView.findViewById(R.id.listView_dictionary);
        //db = new DatabaseHandler(getActivity());
       // List<Contact> contacts = db.getAllContacts();
        //mListView.setAdapter(new ContactAdapter(getActivity(), contacts, R.layout.custom_row_contact));

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
