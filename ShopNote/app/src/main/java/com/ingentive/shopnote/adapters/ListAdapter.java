package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.ListModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class ListAdapter extends BaseAdapter implements View.OnClickListener {

    public List<ListModel> data;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater=null;

    public ListAdapter(Context context, List<ListModel> dataC, int rowId) {

        this.mContext = context;
        this.res = rowId;
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

        View vi = rowView;
        if ( vi == null  ){
            vi = inflater.inflate(res, null);
        }
        TextView itemName;
        ImageView ivOption,ivFavorit,ivSection;

        itemName = (TextView)vi.findViewById(R.id.tvItemName);
        ivOption = (ImageView)vi.findViewById(R.id.ivOpt);
        ivFavorit = (ImageView)vi.findViewById(R.id.ivFav);
        ivSection = (ImageView)vi.findViewById(R.id.ivSection);

        itemName.setText(data.get(postion).getItemName());
        ivOption.setImageResource(data.get(postion).getIconOption());
        ivFavorit.setImageResource(data.get(postion).getIconFavorit());
        ivSection.setImageResource(data.get(postion).getIconSection());
        ivOption.setOnClickListener((View.OnClickListener) this);
        ivFavorit.setOnClickListener((View.OnClickListener) this);
        ivSection.setOnClickListener((View.OnClickListener) this);
        return vi;
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.ivOpt:
                Toast.makeText(mContext, "manu image click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivFav:
                Toast.makeText(mContext, "favorite image click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ivSection:
                Toast.makeText(mContext, "tomato image click", Toast.LENGTH_SHORT).show();
                break;
            default:

        }
    }
}
