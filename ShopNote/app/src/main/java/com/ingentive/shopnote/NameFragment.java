package com.ingentive.shopnote;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class NameFragment extends Fragment {

    DatabaseHandler db;
    ListView mListView;
    NameAdapter nameAdapter;
    private TextView tvId,tvPhone;


    public NameFragment() {
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

        View rootView =  inflater.inflate(R.layout.fragment_name, null);
        mListView = (ListView) rootView.findViewById(R.id.listView_name);
        //db = new DatabaseHandler(getActivity());
       // List<Contact> contacts = db.getAllContacts();
       // mListView.setAdapter(new NameAdapter(getActivity(), contacts, R.layout.custom_row_name));

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
