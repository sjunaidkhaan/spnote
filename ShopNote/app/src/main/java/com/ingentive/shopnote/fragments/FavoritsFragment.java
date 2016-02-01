package com.ingentive.shopnote.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.MainActivity;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.adapters.FavoritesListAdapter;
import com.ingentive.shopnote.model.FavoritListModel;

import java.util.List;


public class FavoritsFragment extends Fragment {

    private SharedPreferences.Editor editor;
    private final String FAVPREFERENCES = "MyFavPrefs";
    private final String favorit_dialog = "favorit_dialog";
    private SharedPreferences prefs;
    private ListView mListView;
    private FavoritesListAdapter mAdapter;
    private List<FavoritListModel> favList;
    private Context context = MainActivity.mContaxt;

    public FavoritsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        //inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add).setVisible(false);
        menu.findItem(R.id.action_history_search).setVisible(false);
        menu.findItem(R.id.action_favorites_search).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorits, null);

        mListView = (ListView) rootView.findViewById(R.id.lv_favorites);
        favList = DatabaseHandler.getInstance(getActivity()).getFavList();
        mAdapter = new FavoritesListAdapter(getActivity(), favList, R.layout.custom_row_favorites);
        mListView.setAdapter(mAdapter);
        return rootView;
    }

    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
                .setMessage("Your favorites list contain\nregular items that you can\n easily add" +
                        "in the future.\n\nYou can marks favorite items\nas you add item to your list.")
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (menuVisible) {
            if (getActivity() != null) {
                favList = DatabaseHandler.getInstance(getActivity()).getFavList();
                mAdapter = new FavoritesListAdapter(getActivity(), favList, R.layout.custom_row_favorites);
                mListView.setAdapter(mAdapter);
            }
            prefs = context.getSharedPreferences(FAVPREFERENCES, context.MODE_PRIVATE);
            String restoredText = prefs.getString(favorit_dialog, null);
            if (restoredText == null) {
                showDialog();
                editor = prefs.edit();
                editor.putString(favorit_dialog, "success");
                editor.commit();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        prefs = this.getActivity().getSharedPreferences(FAVPREFERENCES, Context.MODE_PRIVATE);
//        String restoredText = prefs.getString(favorit_dialog, null);
//        if (restoredText == null) {
//            showDialog();
//            editor = prefs.edit();
//            editor.putString(favorit_dialog, "success");
//            editor.commit();
//        }
        favList = DatabaseHandler.getInstance(getActivity()).getFavList();
        mAdapter = new FavoritesListAdapter(getActivity(), favList, R.layout.custom_row_favorites);
        mListView.setAdapter(mAdapter);
    }
}
