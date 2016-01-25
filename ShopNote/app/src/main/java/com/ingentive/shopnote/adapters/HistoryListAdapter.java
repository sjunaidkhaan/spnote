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
import com.ingentive.shopnote.model.HistoryChildModel;
import com.ingentive.shopnote.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class HistoryListAdapter extends BaseAdapter {

    private List<HistoryModel> data;
    private int res;
    private Context mContext;
    private static LayoutInflater inflater = null;

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
            vh.ivAddDate = (ImageView) vi.findViewById(R.id.iv_add_history_date);

            int id = vi.generateViewId();
            vi.setId(id);
            vi.setTag(vh);
        } else {
            vh = (ViewHolder) vi.getTag();
        }

        final TextView itemName, tvDate;
        final ImageView ivFavorit, ivAdd, ivAddDate;

        vh.tvDate = (TextView) vi.findViewById(R.id.tv_date_history);
        vh.itemName = (TextView) vi.findViewById(R.id.tv_itemname_history);
        vh.ivFavorit = (ImageView) vi.findViewById(R.id.iv_fav_histor);
        vh.ivAdd = (ImageView) vi.findViewById(R.id.iv_add_history);
        vh.ivAddDate = (ImageView) vi.findViewById(R.id.iv_add_history_date);

        if (data.get(postion).isDate()) {
            vh.tvDate.setVisibility(View.VISIBLE);
            vh.tvDate.setText(data.get(postion).getDatePurchased().toString());
            vh.itemName.setVisibility(View.GONE);
            vh.ivFavorit.setBackgroundResource(R.drawable.favorite_unselected);
            vh.ivFavorit.setVisibility(View.INVISIBLE);
            vh.ivAddDate.setVisibility(View.VISIBLE);
            vh.ivAddDate.setBackgroundResource(R.drawable.add_large_unselected);
        } else {
            vh.ivAddDate.setVisibility(View.GONE);
            vh.tvDate.setVisibility(View.GONE);
            vh.itemName.setVisibility(View.VISIBLE);
            vh.itemName.setText(data.get(postion).getItemName());

            vh.ivFavorit.setVisibility(View.VISIBLE);
            vh.ivAdd.setVisibility(View.VISIBLE);

            final boolean itemIsInList = DatabaseHandler.getInstance(mContext).isInList(data.get(postion).getItemName());
            if (itemIsInList) {
                vh.ivAdd.setBackgroundResource(R.drawable.add_selected);
                vh.ivAdd.setOnClickListener(null);
            } else {
                vh.ivAdd.setBackgroundResource(R.drawable.add_unselected);
            }

            boolean itemIsFav = DatabaseHandler.getInstance(mContext).isFavorit(data.get(postion).getItemName().toString());
            if (itemIsFav) {
                vh.ivFavorit.setBackgroundResource(R.drawable.favorite_unselected);
            } else {
                vh.ivFavorit.setBackgroundResource(R.drawable.favorite_selected);
            }

        }
        tvDate = vh.tvDate;
        itemName = vh.itemName;
        ivFavorit = vh.ivFavorit;
        ivAdd = vh.ivAdd;
        ivAddDate = vh.ivAddDate;

        vh.ivFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean itemIsFav = DatabaseHandler.getInstance(mContext).isFavorit(data.get(postion).getItemName().toString());

                if (itemIsFav) {
                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(itemName.getText().toString());

                    ivFavorit.setBackgroundResource(R.drawable.favorite_selected);
                    DatabaseHandler.getInstance(mContext).removeFavorit(remFavItem);

                } else {
                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(itemName.getText().toString());
                    ivFavorit.setBackgroundResource(R.drawable.favorite_unselected);
                    DatabaseHandler.getInstance(mContext).addFavorit(addFavItem);
                }
            }
        });
        vh.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAdd.setBackgroundResource(R.drawable.add_selected);
                String title = DatabaseHandler.getInstance(mContext).getListName();
                int order = DatabaseHandler.getInstance(mContext).getMaxOrderNo();
                order++;
                DatabaseHandler.getInstance(mContext).addCurrentList(new CurrentListModel(order, itemName.getText().toString(), 0, null, title, 1));
            }
        });
        vh.ivAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivAddDate.setBackgroundResource(R.drawable.add_large_selected);

                List<HistoryChildModel> historyList = new ArrayList<HistoryChildModel>();
                historyList = DatabaseHandler.getInstance(mContext).getHisChil(data.get(postion).getDatePurchased());
                for (int i = 0; i < historyList.size(); i++) {
                    String itemname = historyList.get(i).getHisChItemName().toString();
                    String title = DatabaseHandler.getInstance(mContext).getListName();
                    int order = DatabaseHandler.getInstance(mContext).getMaxOrderNo();
                    order++;
                    DatabaseHandler.getInstance(mContext).addCurrentList(new CurrentListModel(order, itemname, 0, null, title, 1));
                    if (title == null || title.isEmpty())
                        title = "My First ShopNote";
                    final boolean itemIsInList = DatabaseHandler.getInstance(mContext).isInList(historyList.get(i).getHisChItemName().toString());
                    if (itemIsInList) {
                        ivAdd.setBackgroundResource(R.drawable.add_selected);
                        ivAdd.setOnClickListener(null);
                    } else {
                        int order_no = DatabaseHandler.getInstance(mContext).getMaxOrderNo();
                        order_no++;
                        DatabaseHandler.getInstance(mContext).addCurrentList(new CurrentListModel(order_no, itemname, 0, null, title, 1));
                        ivAdd.setBackgroundResource(R.drawable.add_unselected);
                        notifyDataSetChanged();
                    }
                }
                notifyDataSetChanged();
            }
        });
        return vi;
    }

    public class ViewHolder {
        TextView itemName, tvDate;
        ImageView ivFavorit, ivAdd, ivAddDate;

    }
}
