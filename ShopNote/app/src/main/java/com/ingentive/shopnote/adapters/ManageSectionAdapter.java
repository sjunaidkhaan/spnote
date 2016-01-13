package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.ManageSectionModel;
import com.ingentive.shopnote.model.SectionModel;
import com.nhaarman.listviewanimations.util.Swappable;

import java.util.List;

/**
 * Created by PC on 12/22/2015.
 */
public class ManageSectionAdapter extends BaseAdapter implements com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.UndoAdapter, Swappable {
    @NonNull
    @Override
    public View getUndoView(final int position, View convertView, @NonNull ViewGroup parent) {

        Button bDelete;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.custom_row_manage_section_undo, parent, false);
            bDelete = (Button)view.findViewById(R.id.dell);
        }else{
            bDelete = (Button)view.findViewById(R.id.dell);
        }

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"yo", Toast.LENGTH_LONG).show();
                data.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @NonNull
    @Override
    public View getUndoClickView(@NonNull View view) {
        Button bDelete = (Button)view.findViewById(R.id.dellll);

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"yo", Toast.LENGTH_LONG).show();
            }
        });
        return view.findViewById(R.id.dellll);
    }

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
    public Object getItem(int position) {
        return data.get(position);
    }


    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }



    @Override
    public View getView(int postion, View rowView, ViewGroup parent) {

        View vi = rowView;
        if (vi == null) {
            vi = inflater.inflate(res, null);
        }

//       vi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setBackgroundColor(Color.GRAY);
//            }
//        });

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


    @Override
    public void swapItems(int positionOne, int positionTwo) {

        Object temp = data.get(positionOne);
        data.set(positionOne, data.get(positionTwo));
        data.set(positionTwo, (ManageSectionModel) temp);

        notifyDataSetChanged();

    }

}
