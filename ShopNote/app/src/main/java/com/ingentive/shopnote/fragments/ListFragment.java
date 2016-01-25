package com.ingentive.shopnote.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.CurrentListAdapter;
import com.ingentive.shopnote.model.CurrentListModel;

import java.util.ArrayList;
import java.util.List;

//jk
public class ListFragment extends Fragment {

    private AlertDialog.Builder alertDialogBuilder;
    private SwipeMenuListView mListView;
    private CurrentListAdapter mAdapter;
    private List<CurrentListModel> currList;
    private final String MyPREFERENCES = "MyPrefs";
    private final String first_time_dialog = "first_time";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

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
        currList = DatabaseHandler.getInstance(getActivity()).getCurrList();
        mAdapter = new CurrentListAdapter(getActivity(), currList, R.layout.custom_row_list);
        mListView.setAdapter(mAdapter);

        ///jk
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setTitle("Delete");
                // add to menu
                deleteItem.setTitleColor(Color.WHITE);
                deleteItem.setTitleSize(18);
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                switch (index) {
                    case 0:
                        CurrentListModel model = new CurrentListModel();
                        model.setCurrListId(mAdapter.getItem(position).getCurrListId());
                        DatabaseHandler.getInstance(getActivity()).deleteItem(model);
                        currList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
                // Toast.makeText(getActivity(), position + " START", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
                //Toast.makeText(getActivity(), position + " CLOSE", Toast.LENGTH_SHORT).show();
            }
        });

        // set MenuStateChangeListener
        mListView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {
            }

            @Override
            public void onMenuClose(int position) {
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                return false;
            }
        });

        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        return rootView;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == 1) {
            mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
            return true;
        }
        if (id == 2) {
            mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (menuVisible) {

            if (getActivity() != null) {
                currList = DatabaseHandler.getInstance(getActivity()).getCurrList();
                mAdapter = new CurrentListAdapter(getActivity(), currList, R.layout.custom_row_list);
                mListView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        prefs = getActivity().getSharedPreferences(MyPREFERENCES, getActivity().MODE_PRIVATE);
        String restoredText = prefs.getString(first_time_dialog, null);
        if (restoredText == null) {
            firstDialog();
            editor = prefs.edit();
            editor.putString(first_time_dialog, "success");
            editor.commit();

            List<CurrentListModel> tempList = new ArrayList<CurrentListModel>();
            CurrentListModel currModel = new CurrentListModel();
            currModel.setCurrListId(1);
            currModel.setOrderNo(1);
            currModel.setItemName("Bread");
            currModel.setQuantity("1");
            currModel.setFavSelectedIcon(R.drawable.favorite_selected);
            tempList.add(currModel);
            currModel = new CurrentListModel();
            currModel.setCurrListId(2);
            currModel.setOrderNo(2);
            currModel.setItemName("Milk");
            currModel.setQuantity("1");
            currModel.setFavSelectedIcon(R.drawable.favorite_selected);
            tempList.add(currModel);
            currModel = new CurrentListModel();
            currModel.setCurrListId(3);
            currModel.setOrderNo(3);
            currModel.setItemName("Cereal");
            currModel.setQuantity("1");
            currModel.setFavSelectedIcon(R.drawable.favorite_selected);
            tempList.add(currModel);

            mAdapter = new CurrentListAdapter(getActivity(), tempList, R.layout.custom_row_list);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        } else {
            currList = DatabaseHandler.getInstance(getActivity()).getCurrList();
            mAdapter = new CurrentListAdapter(getActivity(), currList, R.layout.custom_row_list);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }


    public void firstDialog() {
        //AlertDialog.Builder
        alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Welcome to Shopnote We've started you on your " +
                "first shopping list. Click on the + icon to add to more items or swipe " +
                "left to delete items.");

        alertDialogBuilder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                secondDialog();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void secondDialog() {
        //AlertDialog.Builder
        alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage("Once you're done creating yor list, click" +
                "the 'Shop' button below. This will organize your list by the sections" +
                "of the supermarket.");

        alertDialogBuilder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}