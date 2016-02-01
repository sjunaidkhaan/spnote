package com.ingentive.shopnote.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.MainActivity;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.HistoryListAdapter;
import com.ingentive.shopnote.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;


public class HistoryListFragment extends Fragment {

    private SharedPreferences.Editor editor;
    private final String HISTORYPREFERENCES = "MyHistoryPrefs";
    private final String history_dialog = "history_dialog";
    private SharedPreferences prefs;
    private ExpandableListView mExpHistoryList;
    private HistoryListAdapter mAdapter;
    private List<HistoryModel> historyList;
    private List<HistoryModel> searchItem;
    private SwipeMenuListView mListView;

    private Context context = MainActivity.mContaxt;
    public HistoryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, null);

        mListView = (SwipeMenuListView) rootView.findViewById(R.id.history_list_view);
        getData();
        mAdapter = new HistoryListAdapter(getActivity(), searchItem, R.layout.custom_row_history);
        mListView.setAdapter(mAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getActivity());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("Delete");
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
                        // delete
                        boolean isParent = searchItem.get(position).isDate();
                        if (isParent) {
                            String datePurchased = searchItem.get(position).getDatePurchased();
                            DatabaseHandler.getInstance(getActivity()).deleteDateBaseFromHistory(datePurchased);
                            getData();
                            mAdapter = new HistoryListAdapter(getActivity(), searchItem, R.layout.custom_row_history);
                            mListView.setAdapter(mAdapter);
                        } else {
                            int itemId = searchItem.get(position).getHistoryId();
                            DatabaseHandler.getInstance(getActivity()).deleteItemBaseFromHistory(itemId);
                            searchItem.remove(position);
                            if (searchItem.size() == 1) {
                                searchItem.clear();
                            }
                            mAdapter.notifyDataSetChanged();
                        }
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
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
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
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
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

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
                .setMessage("Your history stores your\ncompleted purchased for\n\t\t\tyour records." +
                        "\n\nAny items that you mark off\nyour list while shopping will\n\t\t" +
                        "be stored in history.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void getData() {
        searchItem = new ArrayList<HistoryModel>();
        historyList = DatabaseHandler.getInstance(getActivity()).getHistory();
        for (int i = 0; i < historyList.size(); i++) {
            boolean exists = false;
            for (int j = 0; j < searchItem.size(); ++j) {
                if (searchItem.get(j).getDatePurchased().compareTo(historyList.get(i).getDatePurchased()) == 0) {
                    exists = true;
                }
            }
            if (!exists) {
                HistoryModel hTemp = new HistoryModel();
                hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                hTemp.setItemName(historyList.get(i).getItemName());
                hTemp.setHistoryId(historyList.get(i).getHistoryId());
                hTemp.setIsDate(true);
                searchItem.add(hTemp);

                hTemp = new HistoryModel();
                hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                hTemp.setItemName(historyList.get(i).getItemName());
                hTemp.setHistoryId(historyList.get(i).getHistoryId());
                hTemp.setIsDate(false);
                searchItem.add(hTemp);
            } else {
                HistoryModel hTemp = new HistoryModel();
                hTemp.setDatePurchased(historyList.get(i).getDatePurchased());
                hTemp.setItemName(historyList.get(i).getItemName());
                hTemp.setHistoryId(historyList.get(i).getHistoryId());
                hTemp.setIsDate(false);
                searchItem.add(hTemp);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        //inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_history_search).setVisible(true);
        menu.findItem(R.id.action_favorites_search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (menuVisible) {
            if (getActivity() != null) {
                getData();
                mAdapter = new HistoryListAdapter(getActivity(), searchItem, R.layout.custom_row_history);
                mListView.setAdapter(mAdapter);
            }
            prefs =context.getSharedPreferences(HISTORYPREFERENCES, context.MODE_PRIVATE);
            String restoredText = prefs.getString(history_dialog, null);
            if (restoredText == null) {
                showDialog();
                editor = prefs.edit();
                editor.putString(history_dialog, "success");
                editor.commit();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        prefs = this.getActivity().getSharedPreferences(HISTORYPREFERENCES, Context.MODE_PRIVATE);
//        String restoredText = prefs.getString(history_dialog, null);
//        if (restoredText == null) {
//            showDialog();
//            editor = prefs.edit();
//            editor.putString(history_dialog, "success");
//            editor.commit();
//        }
        getData();
        mAdapter = new HistoryListAdapter(getActivity(), searchItem, R.layout.custom_row_history);
        mListView.setAdapter(mAdapter);
    }
}
