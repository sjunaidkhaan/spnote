package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.DictionaryModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class DectionaryAdapter extends BaseAdapter {

    public List<DictionaryModel> data;
    public int res;
    public Context context;
    ListView lv;
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
    public View getView(final int postion, View rowView, final ViewGroup viewGroup){

        View vi = rowView;
        ViewHolder vh = new ViewHolder();
        if ( vi == null  ){
            vi = inflater.inflate(res,  viewGroup, false);
            vh.tvItemName = (TextView)vi.findViewById(R.id.tv_item_name);
            vh.ivFavIcon = (ImageView)vi.findViewById(R.id.iv_add_list_fav);
            vh.ivHistIcon = (ImageView)vi.findViewById(R.id.iv_add_list_history);
            int id = vi.generateViewId();
            vi.setId(id);
            vi.setTag(vh);
        } else {
            vh = (ViewHolder) vi.getTag();
        }

        /*TextView tvId, tvItemName,tvSectionId;

        tvId = (TextView)mView.findViewById(R.id.tvId);
        tvItemName = (TextView)mView.findViewById(R.id.tvItemName);
        tvSectionId = (TextView)mView.findViewById(R.id.tvSectionId);

        tvId.setText(data.get(postion).getDictionaryId()+"");
        tvItemName.setText(data.get(postion).getItemName());
        tvSectionId.setText(data.get(postion).getSectionId());
        return mView;*/

        final TextView tvItemName;
        final ImageView ivHistIcon,ivFavIcon;

        vh.tvItemName.setText(data.get(postion).getItemName());

        if ( data.get(postion).getHistoryItem()==1 && data.get(postion).getFavItem()==1){
            vh.ivFavIcon.setVisibility(View.VISIBLE);
            vh.ivHistIcon.setVisibility(View.VISIBLE);

            vh.ivFavIcon.setBackgroundResource(data.get(postion).getFavIcon());
            vh.ivHistIcon.setBackgroundResource(data.get(postion).getHistoryIcon());
        }else if ( data.get(postion).getHistoryItem()==1 ){
            vh.ivFavIcon.setVisibility(View.GONE);
            vh.ivHistIcon.setVisibility(View.VISIBLE);

            vh.ivHistIcon.setBackgroundResource(data.get(postion).getHistoryIcon());
        }else if (data.get(postion).getFavItem()==1 ){
            vh.ivFavIcon.setVisibility(View.VISIBLE);
            vh.ivHistIcon.setVisibility(View.GONE);

            //setting gravity
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)vh.ivFavIcon.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            vh.ivFavIcon.setLayoutParams(params);

            vh.ivFavIcon.setBackgroundResource(data.get(postion).getFavIcon());
        }else{
            vh.ivFavIcon.setVisibility(View.GONE);
            vh.ivHistIcon.setVisibility(View.GONE);
        }
        tvItemName = vh.tvItemName;
        ivFavIcon = vh.ivFavIcon;
        ivHistIcon = vh.ivHistIcon;
        return vi;
    }
    public class ViewHolder
    {
        TextView tvItemName;
        ImageView ivHistIcon,ivFavIcon;

    }
}
