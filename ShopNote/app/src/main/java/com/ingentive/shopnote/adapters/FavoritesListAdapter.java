package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.FavoritListModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class FavoritesListAdapter extends BaseAdapter {

    public List<FavoritListModel> data;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;

    public FavoritesListAdapter(Context context, List<FavoritListModel> dataC, int rowId) {

        this.mContext = context;
        this.res = rowId;
        this.data = dataC;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postion, View rowView, ViewGroup parent) {
        View vi = rowView;
        final TextView itemName;
        final ImageView ivFavorit, ivAdd;
        if (vi == null) {
            vi = inflater.inflate(res, null);
        }

        itemName = (TextView) vi.findViewById(R.id.tv_favorite);
        ivFavorit = (ImageView) vi.findViewById(R.id.iv_fav);
        ivAdd = (ImageView) vi.findViewById(R.id.iv_add);

        itemName.setText(data.get(postion).getItemName());
        ivFavorit.setImageResource(R.drawable.favorite_unselected);


        db = new DatabaseHandler(mContext);
        final boolean itemIsInList = db.isInList(data.get(postion).getItemName().toString());
        //Toast.makeText(mContext,"itemIsFav "+itemIsFav,Toast.LENGTH_LONG).show();
        if (itemIsInList) {
            ivAdd.setBackgroundResource(R.drawable.add_selected);
            ivAdd.setOnClickListener(null);
        } else {
            ivAdd.setBackgroundResource(R.drawable.add_unselected);
        }
        //ivAdd.setImageResource(R.drawable.add_unselected);

        ivFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Favorite Clicked: " + itemName.getText().toString() + ":" + postion, Toast.LENGTH_SHORT).show();
                db = new DatabaseHandler(mContext);
                boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());

                if (itemIsFav) {
                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(itemName.getText().toString());
                    ivFavorit.setImageResource(R.drawable.favorite_selected);
                    db.removeFavorit(remFavItem);

                } else {
                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(itemName.getText().toString());
                    ivFavorit.setImageResource(R.drawable.favorite_unselected);
                    db.addFavorit(addFavItem);
                }

            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAdd.setImageResource(R.drawable.add_selected);
               // Toast.makeText(mContext, "get" + data.get(postion).getItemName(), Toast.LENGTH_SHORT).show();
                db = new DatabaseHandler(mContext);
                String title = db.getListName();
                db = new DatabaseHandler(mContext);
                int order = db.getMaxOrderNo();
                order++;
                db = new DatabaseHandler(mContext);
                db.addCurrentList(new CurrentListModel(order, itemName.getText().toString(), 0, null, title, 1));
            }
        });
        return vi;
    }
}
