package com.ingentive.shopnote.adapters;

import android.content.Context;
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
    TextView itemName;
    ImageView ivOption, ivFavorit_selected,ivFavorit_unselected, ivSection;

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

    @Override
    public View getView(int postion, View rowView, ViewGroup parent) {

        View vi = rowView;
        if (vi == null) {
            vi = inflater.inflate(res, null);
        }


        itemName = (TextView) vi.findViewById(R.id.tvItemName);
        ivOption = (ImageView) vi.findViewById(R.id.ivOpt);
        ivFavorit_selected = (ImageView) vi.findViewById(R.id.ivFav_selected);
        ivSection = (ImageView) vi.findViewById(R.id.ivSection);

        vi.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                String listValue = (String) itemName.getText();
                Toast.makeText(mContext, ""+listValue, Toast.LENGTH_SHORT).show();
            }
        });

        itemName.setText(data.get(postion).getItemName());
        ivOption.setImageResource(R.drawable.grab_grabbed);
        db = new DatabaseHandler(mContext);
        String iconSecton = db.getIconSection(data.get(postion).getItemName().toString());
        db = new DatabaseHandler(mContext);
        boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());

        if (itemIsFav) {
            ivFavorit_selected.setVisibility(View.GONE);
            ivFavorit_unselected.setVisibility(View.VISIBLE);
        }else {
            ivFavorit_selected.setVisibility(View.VISIBLE);
            ivFavorit_unselected.setVisibility(View.GONE);
        }



        /* if ( data.get(postion).getHistoryItem()==1 && data.get(postion).getFavItem()==1){
            ivFavIcon.setVisibility(View.VISIBLE);
            ivHistIcon.setVisibility(View.VISIBLE);

            ivFavIcon.setImageResource(data.get(postion).getFavIcon());
            ivHistIcon.setImageResource(data.get(postion).getHistoryIcon());
        }else if ( data.get(postion).getHistoryItem()==1 ){
            ivFavIcon.setVisibility(View.GONE);
            ivHistIcon.setVisibility(View.VISIBLE);

            ivHistIcon.setImageResource(data.get(postion).getHistoryIcon());*/
             ivFavorit_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "fav image click", Toast.LENGTH_SHORT).show();
                ivFavorit_selected.setVisibility(View.GONE);
                ivFavorit_unselected.setVisibility(View.VISIBLE);
            }
        });
             ivFavorit_unselected.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(mContext, "fav image click", Toast.LENGTH_SHORT).show();
                     ivFavorit_selected.setVisibility(View.VISIBLE);
                     ivFavorit_unselected.setVisibility(View.GONE);
                 }
             });

        switch (iconSecton) {
            case "clothing.png":
                ivSection.setImageResource(R.drawable.clothing);
                break;
            case "house.png":
                ivSection.setImageResource(R.drawable.house);
                break;
            case "pharmacy.png":
                ivSection.setImageResource(R.drawable.pharmacy);
                break;
            case "produce.png":
                ivSection.setImageResource(R.drawable.produce);
                break;
            case "bakery.png":
                ivSection.setImageResource(R.drawable.bakery);
                break;
            case "dry_goods.png":
                ivSection.setImageResource(R.drawable.dry_goods);
                break;
            case "beverages.png":
                ivSection.setImageResource(R.drawable.beverages);
                break;
            case "freezer.png":
                ivSection.setImageResource(R.drawable.freezer);
                break;
            case "dairy.png":
                ivSection.setImageResource(R.drawable.dairy);
                break;
            case "meat.png":
                ivSection.setImageResource(R.drawable.meat);
                break;
            default:
                ivSection.setImageResource(R.drawable.unknown);
                break;
        }
        ivOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "manu image click", Toast.LENGTH_SHORT).show();
            }
        });
        ivSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "section image click", Toast.LENGTH_SHORT).show();
            }
        });

        return vi;
    }
}
