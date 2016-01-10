package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.DictionaryModel;
import com.ingentive.shopnote.model.ListModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class ListAdapter extends BaseAdapter {

    public List<ListModel> data;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;


    public ListAdapter(Context context, List<ListModel> dataC, int rowId) {

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
    public View getView(int postion, View rowView, ViewGroup parent) {


        //jk
        ViewHolder vh = new ViewHolder();
        //

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.custom_row_list, parent,false);
            vh.itemName = (TextView) rowView.findViewById(R.id.tvItemName);
            vh.ivOption = (ImageView) rowView.findViewById(R.id.ivOpt);
            vh.ivFavorit_selected = (ImageView) rowView.findViewById(R.id.ivFav_selected);
            vh.ivSection = (ImageView) rowView.findViewById(R.id.iv_Section);
            int id = rowView.generateViewId();
            rowView.setId(id);
            rowView.setTag(vh);
        }else{
            vh = (ViewHolder)rowView.getTag();
        }
        db = new DatabaseHandler(mContext);
        boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());

        if (itemIsFav) {
            vh.ivFavorit_selected.setVisibility(View.GONE);
        }else {
            vh.ivFavorit_selected.setVisibility(View.VISIBLE);

        }


        vh.itemName.setText(data.get(postion).getItemName());
        vh.ivOption.setBackgroundResource(R.drawable.grab_grabbed);
        db = new DatabaseHandler(mContext);
        String iconSecton = db.getIconSection(data.get(postion).getItemName().toString());


        vh.ivFavorit_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "fav image click", Toast.LENGTH_SHORT).show();
                notifyDataSetInvalidated();
            }
        });

        vh.ivOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "manu image click", Toast.LENGTH_SHORT).show();
            }
        });
        vh.ivSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "section image click", Toast.LENGTH_SHORT).show();
            }
        });


        switch (iconSecton) {
            case "clothing.png":
                vh.ivSection.setBackgroundResource(R.drawable.clothing);
                break;
            case "house.png":
                vh.ivSection.setBackgroundResource(R.drawable.house);
                break;
            case "pharmacy.png":
                vh.ivSection.setBackgroundResource(R.drawable.pharmacy);
                break;
            case "produce.png":
                vh.ivSection.setBackgroundResource(R.drawable.produce);
                break;
            case "bakery.png":
                vh.ivSection.setBackgroundResource(R.drawable.bakery);
                break;
            case "dry_goods.png":
                vh.ivSection.setBackgroundResource(R.drawable.dry_goods);
                break;
            case "beverages.png":
                vh.ivSection.setBackgroundResource(R.drawable.beverages);
                break;
            case "freezer.png":
                vh.ivSection.setBackgroundResource(R.drawable.freezer);
                break;
            case "dairy.png":
                vh.ivSection.setBackgroundResource(R.drawable.dairy);
                break;
            case "meat.png":
                vh.ivSection.setBackgroundResource(R.drawable.meat);
                break;
            default:
                vh.ivSection.setBackgroundResource(R.drawable.unknown);
                break;
        }


        return rowView;
    }

    public class ViewHolder
    {
        TextView itemName;
        ImageView ivOption, ivFavorit_selected,ivFavorit_unselected, ivSection;
    }

}
