package com.ingentive.shopnote.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingentive.shopnote.R;

public class MyFirstNoteFragment extends android.support.v4.app.Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 4 ;
    public int[] tabIcons = {
            R.drawable.list_unselected,
            R.drawable.shop_unselected,
            R.drawable.favoritess_unselected,
            R.drawable.history_unselected
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.getTabAt(0).setCustomView(R.layout.custom_tab_item_list);
                tabLayout.getTabAt(1).setCustomView(R.layout.custom_tab_item_shop);
                tabLayout.getTabAt(2).setCustomView(R.layout.custom_tab_item_fav);
                tabLayout.getTabAt(3).setCustomView(R.layout.custom_tab_item_his);
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
                    return new ShopFragment();
                case 2:
                    return new FavoritsFragment();
                case 3:
                    return new HistoryFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        /**
         * This method returns the title of the tab according to the position.
         */
        @Override
        public CharSequence getPageTitle(int position) {
           /* switch (position) {
                case 0:
                    return "List";
                case 1:
                    return "Shop";
                case 2:
                    return "Favorits";
                case 3:
                    return "History";
            }*/

            return null;
        }
    }
}