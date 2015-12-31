package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.AddListModel;
import com.ingentive.shopnote.model.DictionaryModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class AddListAdapter extends BaseAdapter {

    public List<AddListModel> data;
    public int mRes;
    public Context mContext;
    private static LayoutInflater inflater=null;

    public AddListAdapter(Context context, List<AddListModel> dataC, int rowId) {

        this.mContext = context;
        this.mRes = rowId;
        this.data = dataC;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int postion, View rowView, ViewGroup parent) {

        View mView = rowView;
        if ( mView == null  ){
            mView = inflater.inflate(mRes, null);
        }

        TextView tvItemName;
        ImageView ivHistIcon,ivFavIcon;

        tvItemName = (TextView)mView.findViewById(R.id.tv_item_name);
        ivFavIcon = (ImageView)mView.findViewById(R.id.iv_fav);
        ivHistIcon = (ImageView)mView.findViewById(R.id.iv_history);

        tvItemName.setText(data.get(postion).getItemName());
        ivFavIcon.setImageResource(data.get(postion).getFavIcon());
        ivHistIcon.setImageResource(data.get(postion).getHistrIcon());

        return mView;
    }
}
