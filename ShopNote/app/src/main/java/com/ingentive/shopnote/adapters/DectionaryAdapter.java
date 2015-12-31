package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.Contact;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.DictionaryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class DectionaryAdapter extends BaseAdapter {

    public List<DictionaryModel> data;
    public int res;
    public Context context;
    private static LayoutInflater inflater=null;

    public DectionaryAdapter(Context context, List<DictionaryModel> dataC, int rowId) {

        this.context = context;
        this.res = rowId;
        this.data = dataC;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            mView = inflater.inflate(res, null);
        }

        /*TextView tvId, tvItemName,tvSectionId;

        tvId = (TextView)mView.findViewById(R.id.tvId);
        tvItemName = (TextView)mView.findViewById(R.id.tvItemName);
        tvSectionId = (TextView)mView.findViewById(R.id.tvSectionId);

        tvId.setText(data.get(postion).getDictionaryId()+"");
        tvItemName.setText(data.get(postion).getItemName());
        tvSectionId.setText(data.get(postion).getSectionId());
        return mView;*/

        TextView tvItemName;
        ImageView ivHistIcon,ivFavIcon;

        tvItemName = (TextView)mView.findViewById(R.id.tv_item_name);
        ivFavIcon = (ImageView)mView.findViewById(R.id.iv_add_list_fav);
        ivHistIcon = (ImageView)mView.findViewById(R.id.iv_add_list_history);

        tvItemName.setText(data.get(postion).getItemName());
        ivFavIcon.setImageResource(data.get(postion).getFavIcon());
        ivHistIcon.setImageResource(data.get(postion).getHistoryIcon());

        return mView;
    }
}
