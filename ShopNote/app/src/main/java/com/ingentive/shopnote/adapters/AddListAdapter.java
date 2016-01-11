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

import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.AddListModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class AddListAdapter extends BaseAdapter {

    public List<AddListModel> data;
    public int mRes;
    public Context mContext;
    private static LayoutInflater inflater = null;

    public AddListAdapter(Context context, List<AddListModel> dataC, int rowId) {

        this.mContext = context;
        this.mRes = rowId;
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
    public View getView(final int postion, View rowView, final ViewGroup viewGroup) {

        View vi = rowView;
        ViewHolder vh = new ViewHolder();
        if (vi == null) {
            vi = inflater.inflate(mRes, viewGroup, false);
            vh.tvItemName = (TextView) vi.findViewById(R.id.tv_item_name);
            vh.ivFavIcon = (ImageView) vi.findViewById(R.id.iv_add_list_fav);
            vh.ivHistIcon = (ImageView) vi.findViewById(R.id.iv_add_list_history);
            int id = vi.generateViewId();
            vi.setId(id);
            vi.setTag(vh);
        } else {
            vh = (ViewHolder) vi.getTag();
        }

        final TextView tvItemName;
        final ImageView ivHistIcon, ivFavIcon;

        vh.tvItemName.setText(data.get(postion).getItemName());
        vh.ivFavIcon.setBackgroundResource(data.get(postion).getFavIcon());
        vh.ivHistIcon.setBackgroundResource(data.get(postion).getHistrIcon());

        tvItemName = vh.tvItemName;
        ivFavIcon = vh.ivFavIcon;
        ivHistIcon = vh.ivHistIcon;
        return vi;
    }

    public class ViewHolder {
        TextView tvItemName, tvQuantity;
        ImageView ivHistIcon, ivFavIcon;

    }

}

