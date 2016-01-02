package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.FavoritListModel;
import com.ingentive.shopnote.model.ListModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class CurrentListAdapter extends ArrayAdapter<CurrentListModel> {

    public List<CurrentListModel> data;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;

    public CurrentListAdapter(Context context, List<CurrentListModel> dataC, int rowId) {
        super(context,rowId,dataC);

        this.mContext = context;
        this.res = rowId;
        this.data = dataC;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int postion, View rowView, ViewGroup parent) {

        View vi = rowView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi = inflater.inflate(R.layout.custom_row_list,parent, false);

        final TextView itemName;
        final ImageView ivOption, ivFavorit_selected, ivSection;

        itemName = (TextView) vi.findViewById(R.id.tvItemName);
        ivOption = (ImageView) vi.findViewById(R.id.ivOpt);
        ivFavorit_selected = (ImageView) vi.findViewById(R.id.ivFav_selected);
        ivSection = (ImageView) vi.findViewById(R.id.ivSection);

        itemName.setText(data.get(postion).getItemName());
        ivOption.setImageResource(R.drawable.grab_grabbed);
        ivFavorit_selected.setImageResource(R.drawable.favorite_selected);
        db = new DatabaseHandler(mContext);

        boolean isFav = db.isFavorit(itemName.getText().toString());
        if ( isFav ){
            ivFavorit_selected.setImageResource(R.drawable.favorite_unselected);
        }

        ivOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "manu image click" + postion, Toast.LENGTH_SHORT).show();
            }
        });
        ivSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "section image click" + postion, Toast.LENGTH_SHORT).show();
            }
        });

        ivFavorit_selected.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {


                Toast.makeText(mContext, "Favorite Clicked: "+ itemName.getText().toString() + ":" + postion, Toast.LENGTH_SHORT).show();

                db = new DatabaseHandler(mContext);
                boolean itemIsFav = db.isFavorit(data.get(postion).getItemName().toString());


                if ( itemIsFav ){

                    FavoritListModel remFavItem = new FavoritListModel();
                    remFavItem.setItemName(itemName.getText().toString());

                    ivFavorit_selected.setImageResource(R.drawable.favorite_selected);
                    db.removeFavorit(remFavItem);

                }else{

                    FavoritListModel addFavItem = new FavoritListModel();
                    addFavItem.setItemName(itemName.getText().toString());
                    ivFavorit_selected.setImageResource(R.drawable.favorite_unselected);
                    db.addFavorit(addFavItem);
                }

            }
        });



        String iconSecton = db.getIconSection(data.get(postion).getItemName().toString());

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


        return vi;
    }
}
