package com.ingentive.shopnote.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.ShopCustomAdapter;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.HistoryModel;
import com.ingentive.shopnote.model.ShopChildModel;
import com.ingentive.shopnote.model.ShopParentModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ShopFragment extends Fragment {

    public static SharedPreferences.Editor editor;
    public static final String MYPREF = "MyPref";
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
    Button btnFinishShopping;


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
        prefs = this.getActivity().getSharedPreferences(MYPREF, Context.MODE_PRIVATE);
        String restoredText = prefs.getString(first_time_dialog, null);
        if (restoredText == null) {
            showDialog();
            editor = prefs.edit();
            editor.putString(first_time_dialog, "success");
            editor.commit();
        }
        mExpShopList = (ExpandableListView) rootView.findViewById(R.id.expandable_shop_list);
        btnFinishShopping = (Button) rootView.findViewById(R.id.btn_finish_shopping);
        getData();
        mAdapter = new ShopCustomAdapter(getActivity(), shopList);
        mExpShopList.setAdapter(mAdapter);
        shopParList = db.getShopParSection();
        //mAdapter.notifyDataSetChanged();
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

    public void finishDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(getActivity());
        myAlertDialog.setTitle("Finish Shopping?");
        myAlertDialog.setMessage("Your checked items will be cleared and stored" +
                " in your history for you to reference in the future.");

        myAlertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
                // do something when the Cancel button is clicked
            }
        });
        myAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // do something when the OK button is clicked
                db = new DatabaseHandler(getActivity());
                List<CurrentListModel> currList = db.getCurrList();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd");
                String formattedDate = df.format(c.getTime());
                System.out.println("day, month " + formattedDate);
                // Toast.makeText(getActivity(), formattedDate, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < currList.size(); i++) {
                    if (currList.get(i).getChecked() == 1) {
                        HistoryModel historObj = new HistoryModel();
                        historObj.setDatePurchased(formattedDate);
                        historObj.setItemName(currList.get(i).getItemName().toString());
                        historObj.setQuantity(currList.get(i).getQuantity().toString());
                        db = new DatabaseHandler(getActivity());
                        db.addHistory(historObj);
                        CurrentListModel currentListModel =new CurrentListModel();
                        currentListModel= currList.get(i);
                        db = new DatabaseHandler(getActivity());
                        db.deleteItem(currentListModel);
                        getData();
                        mAdapter = new ShopCustomAdapter(getActivity(), shopList);
                        mExpShopList.setAdapter(mAdapter);
                    }
                }
            }
        });

        myAlertDialog.show();
    }
    public void getData(){
        db = new DatabaseHandler(getActivity());
        shopParList = db.getShopParSection();
        btnFinishShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishDialog();
            }
        });
        // db = new DatabaseHandler(getActivity());
        //shopChiList = db.getShopChil();
        shopList = new ArrayList<ShopParentModel>();
        for (int i = 0; i < shopParList.size(); i++) {
            db = new DatabaseHandler(getActivity());
            shopChiList = new ArrayList<ShopChildModel>();
            shopChiList = db.getShopChilData(shopParList.get(i).getShopPaId());
            ShopParentModel parentModel = new ShopParentModel();
            if (shopChiList.size() != 0) {
                parentModel.setShopPaSectionName(shopParList.get(i).getShopPaSectionName().toString());
                parentModel.setShopPaSectionIcon(shopParList.get(i).getShopPaSectionIcon().toString());
                parentModel.setArrayChildren(shopChiList);
                parentModel.setIsClick(false);
                shopList.add(parentModel);
                //Toast.makeText(getActivity(), " " + shopChiList.size(), Toast.LENGTH_LONG).show();
            }
        }
        if(shopList.size()==0){
            btnFinishShopping.setVisibility(View.GONE);
        }else{
            btnFinishShopping.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if ( menuVisible ){
            Log.d("I am fragment", "2");
            shopList = new ArrayList<ShopParentModel>();
            getData();
            mAdapter = new ShopCustomAdapter(getActivity(), shopList);
            mExpShopList.setAdapter(mAdapter);
        }

    }
}