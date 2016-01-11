package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.ManageSectionModel;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class ManageSectionAdapter extends BaseAdapter {

    public List<ManageSectionModel> data;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;

    public ManageSectionAdapter(Context context, List<ManageSectionModel> dataC, int rowId) {

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

       vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(Color.GRAY);
            }
        });

        TextView sectionName;
        ImageView ivOption, ivSection;
        sectionName = (TextView) vi.findViewById(R.id.tvSectionName_ms);
        ivOption = (ImageView) vi.findViewById(R.id.ivOpt_ms);
        ivSection = (ImageView) vi.findViewById(R.id.ivSection_ms);
        ivOption.setImageResource(R.drawable.grab_notgrabbed);
        sectionName.setText(data.get(postion).getSectionName());
               //ivSection.setImageResource(data.get(postion).getSectionIcon());

       /* db = new DatabaseHandler(mContext);
        String iconSecton = db.getIconSection(data.get(postion).getItemName().toString());
        db = new DatabaseHandler(mContext);*/
        if(!data.get(postion).getSectionName().equals("Unknown")){
            switch (data.get(postion).getSectionName()) {
                case "Clothing":
                    ivSection.setBackgroundResource(R.drawable.clothing);
                    break;
                case "Household and Specialty":
                    ivSection.setBackgroundResource(R.drawable.house);
                    break;
                case "Pharmacy":
                    ivSection.setBackgroundResource(R.drawable.pharmacy);
                    break;
                case "Produce":
                    ivSection.setBackgroundResource(R.drawable.produce);
                    break;
                case "Bakery":
                    ivSection.setBackgroundResource(R.drawable.bakery);
                    break;
                case "Packaged Foodstuff":
                    ivSection.setBackgroundResource(R.drawable.dry_goods);
                    break;
                case "Beverages":
                    ivSection.setBackgroundResource(R.drawable.beverages);
                    break;
                case "Frozen Food":
                    ivSection.setBackgroundResource(R.drawable.freezer);
                    break;
                case "Dairy and Refridgerated":
                    ivSection.setBackgroundResource(R.drawable.dairy);
                    break;
                case "Meat and Seafood":
                    ivSection.setBackgroundResource(R.drawable.meat);
                    break;
                default:
                    ivSection.setBackgroundResource(R.drawable.unknown);
                    break;
            }
        }
        return vi;
    }
}
