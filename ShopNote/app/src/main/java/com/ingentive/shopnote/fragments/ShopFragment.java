package com.ingentive.shopnote.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.ShopCustomAdapter;
import com.ingentive.shopnote.model.ShopChildModel;
import com.ingentive.shopnote.model.ShopParentModel;

import java.util.ArrayList;
import java.util.List;


public class ShopFragment extends Fragment {

    public static SharedPreferences.Editor editor;
    public static final String MYPREFERENCES = "MyPrefs";
    public static final String dbCreated = "dbKey";
    public static final String first_time_dialog = "first_time";
    public static SharedPreferences prefs;
    private ExpandableListView mExpShopList;
    DatabaseHandler db;
    ShopCustomAdapter mAdapter;
    ArrayList<String> arrayPar = new ArrayList<String>();
    ShopParentModel parentModel = new ShopParentModel();
    List<ShopParentModel> shopList = new ArrayList<ShopParentModel>();
    List<ShopChildModel> shopChiList = new ArrayList<ShopChildModel>();
    List<ShopParentModel> shopParList;


    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shop, null);
        prefs = this.getActivity().getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        String restoredText = prefs.getString(first_time_dialog, null);
        if (restoredText == null) {
            showDialog();
            editor = prefs.edit();
            editor.putString(first_time_dialog, "success");
            editor.commit();
        }
        mExpShopList = (ExpandableListView) rootView.findViewById(R.id.expandable_shop_list);

        db = new DatabaseHandler(getActivity());
        shopParList = db.getShopParSection();
       // db = new DatabaseHandler(getActivity());
        //shopChiList = db.getShopChil();
        shopList = new ArrayList<ShopParentModel>();
        for (int i = 0; i < shopParList.size(); i++) {
           db = new DatabaseHandler(getActivity());
            shopChiList = new ArrayList<ShopChildModel>();
            shopChiList = db.getShopChilData(shopParList.get(i).getShopPaId());
            ShopParentModel parentModel = new ShopParentModel();
            if(shopChiList.size()!=0){
                parentModel.setShopPaSectionName(shopParList.get(i).getShopPaSectionName().toString());
                parentModel.setShopPaSectionIcon(shopParList.get(i).getShopPaSectionIcon().toString());
                parentModel.setArrayChildren(shopChiList);
                shopList.add(parentModel);
                Toast.makeText(getActivity(), " " + shopChiList.size(), Toast.LENGTH_LONG).show();
            }
        }
        mAdapter = new ShopCustomAdapter(getActivity(), shopList);
        mExpShopList.setAdapter(mAdapter);
        return rootView;
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setMessage("The Shop screen organizes items into sections and aisles " +
                        "and allows you check them off. Use this view in store to help " +
                        "you with your shopping.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}