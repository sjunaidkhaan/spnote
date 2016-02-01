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
import android.widget.AdapterView;
import android.widget.Button;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.ShopCustomAdapterSimpleListview;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.HistoryModel;
import com.ingentive.shopnote.model.ShopChildModel;
import com.ingentive.shopnote.model.ShopParentModel;
import com.ingentive.shopnote.model.ShopParentModelMerger;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ShopFragmentSimpleListview extends Fragment {

    private SharedPreferences.Editor editor;
    private final String ShopFragment = "ShopFragment";
    private final String shopfragment_dialog = "shopfragment_dialog";
    private SharedPreferences prefs;
    private DynamicListView mExpShopList;
    private ShopCustomAdapterSimpleListview mAdapter;
    private List<ShopParentModel> shopList = new ArrayList<ShopParentModel>();
    private List<ShopChildModel> shopChiList = new ArrayList<ShopChildModel>();
    private List<ShopParentModel> shopParList;
    private Button btnFinishShopping;
    private ArrayList<ShopParentModelMerger> finalList = new ArrayList<ShopParentModelMerger>();
    int from = -100;


    public ShopFragmentSimpleListview() {
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
        View rootView = inflater.inflate(R.layout.fragment_shop_simple_listview, null);

        mExpShopList = (DynamicListView) rootView.findViewById(R.id.expandable_shop_list);
        btnFinishShopping = (Button) rootView.findViewById(R.id.btn_finish_shopping);
        getData();
        mAdapter = new ShopCustomAdapterSimpleListview(getActivity(), finalList, shopList);
        mExpShopList.setAdapter(mAdapter);

        mExpShopList.enableDragAndDrop();
        mExpShopList.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(final AdapterView<?> parent, final View view,
                                                   final int position, final long id) {
                        if (!finalList.get(position).isParent()) {
                            mExpShopList.startDragging(position);
                            from = finalList.get(position).getShopPaId();
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
        );
        mExpShopList.setOnItemMovedListener(new OnItemMovedListener() {
            @Override
            public void onItemMoved(int originalPosition, int newPosition) {
                int to = finalList.get(newPosition - 1).getShopPaId();
                // Log.d("from:" + originalPosition + "|||" + finalList.get(originalPosition - 1).getShopPaId(), "to:" + newPosition + "|||" + finalList.get(newPosition - 1).getShopPaId());
                if (to != from) {
                    String itemName = finalList.get(newPosition).getShop_chil_item_name();
                    finalList.get(newPosition).getShop_chil_id();
                    //db = new DatabaseHandler(getActivity());
                    DatabaseHandler.getInstance(getActivity()).secAssignToItem(itemName, to);
                    getData();
                    mExpShopList.setAdapter(new ShopCustomAdapterSimpleListview(getActivity(), finalList, shopList));

                }
            }
        });
        return rootView;
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setMessage("The Shop screen organizes\nitems into sections and aisles\n" +
                        "\t\tand allows you check\n\t\t\t\t\tthem off.\n\nUse this view in store to help\n" +
                        "\t\tyou with your shopping.")
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
        myAlertDialog.setTitle("\t\t\tFinish Shopping?");
        myAlertDialog.setMessage("\tYour checked items will be\n\tcleared and stored" +
                " in your\n\thistory for you to reference\n\t\t\t\t\t\tin the future.");

        myAlertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        myAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                List<CurrentListModel> currList = DatabaseHandler.getInstance(getActivity()).getCurrList();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd");
                String formattedDate = df.format(c.getTime());
                for (int i = 0; i < currList.size(); i++) {
                    if (currList.get(i).getChecked() == 1) {
                        HistoryModel historObj = new HistoryModel();
                        historObj.setDatePurchased(formattedDate);
                        historObj.setItemName(currList.get(i).getItemName().toString());
                        historObj.setQuantity(currList.get(i).getQuantity().toString());
                        DatabaseHandler.getInstance(getActivity()).addHistory(historObj);
                        CurrentListModel currentListModel = new CurrentListModel();
                        currentListModel = currList.get(i);
                        DatabaseHandler.getInstance(getActivity()).deleteItem(currentListModel);
                        getData();
                        DatabaseHandler.getInstance(getActivity()).close();
                        mAdapter = new ShopCustomAdapterSimpleListview(getActivity(), finalList, shopList);
                        mExpShopList.setAdapter(mAdapter);
                    }
                }
            }
        });
        myAlertDialog.show();
    }

    public void getData() {
        shopParList = DatabaseHandler.getInstance(getActivity()).getShopParSection();
        btnFinishShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishDialog();
            }
        });
        shopList = new ArrayList<ShopParentModel>();
        for (int i = 0; i < shopParList.size(); i++) {
            shopChiList = new ArrayList<ShopChildModel>();
            shopChiList = DatabaseHandler.getInstance(getActivity()).getShopChilData(shopParList.get(i).getShopPaId());
            ShopParentModel parentModel = new ShopParentModel();
            if (shopChiList.size() != 0) {
                parentModel.setShopPaId(shopParList.get(i).getShopPaId());
                parentModel.setShopPaSectionName(shopParList.get(i).getShopPaSectionName().toString());
                parentModel.setShopPaSectionIcon(shopParList.get(i).getShopPaSectionIcon().toString());
                parentModel.setArrayChildren(shopChiList);
                parentModel.setIsClick(false);
                parentModel.setCounter(0);
                shopList.add(parentModel);

            }
        }
        if (shopList.size() == 0) {
            btnFinishShopping.setVisibility(View.GONE);
        } else {
            btnFinishShopping.setVisibility(View.VISIBLE);
        }

        finalList.clear();

        for (int i = 0; i < shopList.size(); ++i) {
            ShopParentModelMerger tParent = new ShopParentModelMerger();
            tParent.setIsParent(true);
            tParent.setIsClick(true);
            tParent.setShopPaId(shopList.get(i).getShopPaId());
            tParent.setShopPaSectionName(shopList.get(i).getShopPaSectionName().toString());
            tParent.setShopPaSectionIcon(shopList.get(i).getShopPaSectionIcon().toString());

            finalList.add(tParent);
            int counter=0;
            for (int j = 0; j < shopList.get(i).getArrayChildren().size(); ++j) {
                ShopParentModelMerger tChild = new ShopParentModelMerger();
                tChild.setIsParent(false);
                tChild.setIsClick(false);
                tChild.setShopPaId(shopList.get(i).getShopPaId());
                tChild.setShopPaSectionName(shopList.get(i).getShopPaSectionName().toString());
                tChild.setShopPaSectionIcon(shopList.get(i).getShopPaSectionIcon().toString());
                tChild.setShop_chil_id(shopList.get(i).getArrayChildren().get(j).getShopChId());
                tChild.setShop_chil_item_name(shopList.get(i).getArrayChildren().get(j).getShopChItemName());
                tChild.setCheckBox(shopList.get(i).getArrayChildren().get(j).getCheckBox());
                tChild.setShop_chil_quantity(shopList.get(i).getArrayChildren().get(j).getShopChQuantity());
                tChild.setShop_selec_icon(shopList.get(i).getArrayChildren().get(j).getShopChSectionId());
                if(shopList.get(i).getArrayChildren().get(j).getCheckBox()==1){
                    counter++;
                }
                finalList.add(tChild);
            }

        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        if (menuVisible) {
            shopList = new ArrayList<ShopParentModel>();
            getData();
            mAdapter = new ShopCustomAdapterSimpleListview(getActivity(), finalList, shopList);
            mExpShopList.setAdapter(mAdapter);

            prefs = this.getActivity().getSharedPreferences(ShopFragment, Context.MODE_PRIVATE);
            String restoredText = prefs.getString(shopfragment_dialog, null);
            if (restoredText == null) {
                showDialog();
                editor = prefs.edit();
                editor.putString(shopfragment_dialog, "success");
                editor.commit();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        shopList = new ArrayList<ShopParentModel>();
        getData();
        mAdapter = new ShopCustomAdapterSimpleListview(getActivity(), finalList, shopList);
        mExpShopList.setAdapter(mAdapter);
    }
}