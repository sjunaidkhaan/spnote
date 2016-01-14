package com.ingentive.shopnote.fragments;

import android.content.pm.ApplicationInfo;
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

import java.util.List;

//jk
public class ListFragment extends Fragment {

    SwipeMenuListView mListView;
    DatabaseHandler db;
    CurrentListAdapter mAdapter;
    List<CurrentListModel> currList;
    private List<ApplicationInfo> mAppList;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(getActivity(),"ListFragment onCreate ",Toast.LENGTH_LONG).show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, null);
        mListView = (SwipeMenuListView) rootView.findViewById(R.id.lv_list);
        db = new DatabaseHandler(getActivity());
        currList = db.getCurrList();
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
                        // delete
//					delete(item);
                        db = new DatabaseHandler(getActivity());
                        CurrentListModel model = new CurrentListModel();
                        model.setCurrListId(mAdapter.getItem(position).getCurrListId());
                       db.deleteItem(model);
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
                //Toast.makeText(getActivity(), position + " OPEN", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMenuClose(int position) {
                //Toast.makeText(getActivity(), position + " CLOSE", Toast.LENGTH_SHORT).show();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                //Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        ///

        mListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        //Toast.makeText(getActivity(),"size "+currList.size(), Toast.LENGTH_SHORT).show();
        return rootView;
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

//    private void open(ApplicationInfo item) {
//        // open app
//        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
//        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        resolveIntent.setPackage(item.packageName);
//        List<ResolveInfo> resolveInfoList = getActivity().getPackageManager()
//                .queryIntentActivities(resolveIntent, 0);
//        if (resolveInfoList != null && resolveInfoList.size() > 0) {
//            ResolveInfo resolveInfo = resolveInfoList.get(0);
//            String activityPackageName = resolveInfo.activityInfo.packageName;
//            String className = resolveInfo.activityInfo.name;
//
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            ComponentName componentName = new ComponentName(
//                    activityPackageName, className);
//
//            intent.setComponent(componentName);
//            startActivity(intent);
//        }
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Toast.makeText(getActivity(), "id" + id, Toast.LENGTH_LONG).show();
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
        if ( menuVisible ){

        }

    }
}