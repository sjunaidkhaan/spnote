package com.ingentive.shopnote.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CustomViewPager;

public class MyFirstNoteFragment extends android.support.v4.app.Fragment {

    public static TabLayout tabLayout;
    public static CustomViewPager viewPager;
    public static int int_items = 4;
    public int[] tabIcons = {
            R.drawable.list_unselected,
            R.drawable.shop_unselected,
            R.drawable.favorites_unselected,
            R.drawable.history_unselected
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.tab_layout, container, false);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (CustomViewPager) x.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        viewPager.setPagingEnabled(false);

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setCustomView(R.layout.custom_tab_item_list);
                tabLayout.getTabAt(1).setCustomView(R.layout.custom_tab_item_shop);
                tabLayout.getTabAt(2).setCustomView(R.layout.custom_tab_item_fav);
                tabLayout.getTabAt(3).setCustomView(R.layout.custom_tab_item_his);

                tabLayout.getTabAt(0).getCustomView().setBackgroundResource(R.drawable.list_selected);

                tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int pos = tab.getPosition();

                        if (pos == 0) {
                            tab.getCustomView().setBackgroundResource(R.drawable.list_selected);
                            viewPager.setCurrentItem(0);
                        } else if (pos == 1) {
                            tab.getCustomView().setBackgroundResource(R.drawable.shop_selected_tab_icon);
                            viewPager.setCurrentItem(1);
                        } else if (pos == 2) {
                            tab.getCustomView().setBackgroundResource(R.drawable.favorite_select_tab_icon);
                            viewPager.setCurrentItem(2);
                        } else {
                            tab.getCustomView().setBackgroundResource(R.drawable.history_selectd_tab_icon);
                            viewPager.setCurrentItem(3);
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        int pos = tab.getPosition();
                        if (pos == 0) {
                            tab.getCustomView().setBackgroundResource(R.drawable.list_unselected);
                        } else if (pos == 1) {
                            tab.getCustomView().setBackgroundResource(R.drawable.shop_unselected);
                        } else if (pos == 2) {
                            tab.getCustomView().setBackgroundResource(R.drawable.favorites_unselected);
                        } else {
                            tab.getCustomView().setBackgroundResource(R.drawable.history_unselected);
                        }
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        });
        return x;
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ListFragment();
                case 1:
                    return new ShopFragmentSimpleListview();
                case 2:
                    return new FavoritsFragment();
                case 3:
                    return new HistoryListFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}