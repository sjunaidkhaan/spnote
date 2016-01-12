package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
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
import com.ingentive.shopnote.model.HistoryModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class HistoryListAdapter extends BaseAdapter {

    public List<HistoryModel> data;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;

    public HistoryListAdapter(Context context, List<HistoryModel> dataC, int rowId) {

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(final int postion, View rowView, ViewGroup parent) {

        View vi = rowView;
        ViewHolder vh = new ViewHolder();

        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.custom_row_history, parent, false);

            vh.tvDate = (TextView) vi.findViewById(R.id.tv_date_history);
            vh.itemName = (TextView) vi.findViewById(R.id.tv_itemname_history);
            vh.ivFavorit = (ImageView) vi.findViewById(R.id.iv_fav_histor);
            vh.ivAdd = (ImageView) vi.findViewById(R.id.iv_add_history);
            int id = vi.generateViewId();
            vi.setId(id);
            vi.setTag(vh);
        } else {
            vh = (ViewHolder) vi.getTag();
        }


        final TextView itemName, tvDate;
        final ImageView ivFavorit, ivAdd;

        vh.tvDate = (TextView) vi.findViewById(R.id.tv_date_history);
        vh.itemName = (TextView) vi.findViewById(R.id.tv_itemname_history);
        vh.ivFavorit = (ImageView) vi.findViewById(R.id.iv_fav_histor);
        vh.ivAdd = (ImageView) vi.findViewById(R.id.iv_add_history);

        if (data.get(postion).isDate()) {
            vh.tvDate.setVisibility(View.VISIBLE);
            vh.tvDate.setText(data.get(postion).getDatePurchased().toString());
            vh.itemName.setVisibility(View.GONE);
            vh.ivFavorit.setBackgroundResource(R.drawable.favorite_unselected);
            vh.ivFavorit.setVisibility(View.INVISIBLE);
        } else {
            vh.tvDate.setVisibility(View.GONE);
//            tvDate.setText(data.get(postion).getDatePurchased().toString());
            vh.itemName.setVisibility(View.VISIBLE);
            vh.itemName.setText(data.get(postion).getItemName());

            vh.ivFavorit.setVisibility(View.VISIBLE);
            vh.ivAdd.setVisibility(View.VISIBLE);
            //ivFavorit.setImageResource(R.drawable.favorite_unselected);

            db = new DatabaseHandler(mContext);
            final boolean itemIsInList = db.isInList(data.get(postion).getItemName());
            //Toast.makeText(mContext,"itemIsFav "+itemIsFav,Toast.LENGTH_LONG).show();
            if (itemIsInList) {
                vh.ivAdd.setBackgroundResource(R.drawable.add_selected);
                vh.ivAdd.setOnClickListener(null);
            } else {
                vh.ivAdd.setBackgroundResource(R.drawable.add_unselected);
            }
            //vh.ivAdd.setBackgroundResource(R.drawable.add_unselected);
           // Toast.makeText(mContext, "getItemName " + data.get(postion).getItemName().toString(), Toast.LENGTH_LONG).show();
            //Log.d("HistoryListAdapter ","datePurchased "+data.get(postion).getDatePurchased().toString());

            db = new DatabaseHandler(mContext);
            boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());
            if (itemIsFav) {
                vh.ivFavorit.setBackgroundResource(R.drawable.favorite_unselected);
            } else {
                vh.ivFavorit.setBackgroundResource(R.drawable.favorite_selected);
            }

        }
        tvDate=vh.tvDate;
        itemName=vh.itemName;
        ivFavorit=vh.ivFavorit;
        ivAdd=vh.ivAdd;

        //}
        vh.ivFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Favorite Clicked: " + itemName.getText().toString() + ":" + postion, Toast.LENGTH_SHORT).show();
                db = new DatabaseHandler(mContext);
                boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());

                if (itemIsFav) {
                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(itemName.getText().toString());

                    ivFavorit.setBackgroundResource(R.drawable.favorite_selected);
                    db.removeFavorit(remFavItem);

                } else {
                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(itemName.getText().toString());
                    ivFavorit.setBackgroundResource(R.drawable.favorite_unselected);
                    db.addFavorit(addFavItem);
                }
            }
        });
        vh.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAdd.setImageResource(R.drawable.add_selected);
                //Toast.makeText(mContext, "get" + data.get(postion).getItemName(), Toast.LENGTH_SHORT).show();
                db = new DatabaseHandler(mContext);
                db.addCurrentList(new CurrentListModel(1, itemName.getText().toString(), 0, null, "My Firts Shopnote", 1));
            }
        });
        return vi;
    }

    public class ViewHolder {
        TextView itemName, tvDate;
        ImageView ivFavorit, ivAdd;

    }
}
