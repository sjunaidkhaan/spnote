package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ingentive.shopnote.ActivityManageSections;
import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.ManageSectionModel;
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
            bDelete = (Button) view.findViewById(R.id.button_delete);
        } else {
            bDelete = (Button) view.findViewById(R.id.button_delete);
        }

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "yo", Toast.LENGTH_LONG).show();
                data.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @NonNull
    @Override
    public View getUndoClickView(@NonNull View view) {

        return view.findViewById(R.id.button_dont_remove);
    }

    public List<ManageSectionModel> data;
    public int res;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;
    public static SharedPreferences.Editor editor;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String unknownSection = "unknown_section";
    public static SharedPreferences prefs;

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(final int postion, final View rowView, final ViewGroup parent) {

        View vi = rowView;
        ViewHolder vh = new ViewHolder();
        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.custom_row_manage_section, parent, false);

            vh.sectionName = (TextView) vi.findViewById(R.id.tvSectionName_ms);
            vh.ivOption = (ImageView) vi.findViewById(R.id.ivOpt_ms);
            vh.ivSection = (ImageView) vi.findViewById(R.id.ivSection_ms);
            vh.rl_root = (RelativeLayout)vi.findViewById(R.id.custom_row_list);
            SharedPreferences sharedpreferences = mContext.getSharedPreferences("unknown_section", Context.MODE_PRIVATE);

            int id = vi.generateViewId();
            vi.setId(id);
            vi.setTag(vh);
        } else {
            vh = (ViewHolder) vi.getTag();
        }

        final TextView sectionName;
        final ImageView ivOption, ivSection;


        vh.ivOption.setImageResource(R.drawable.grab_notgrabbed);
        vh.sectionName.setText(data.get(postion).getSectionName());
        //ivSection.setImageResource(data.get(postion).getSectionIcon());

       /* db = new DatabaseHandler(mContext);
        String iconSecton = db.getIconSection(data.get(postion).getItemName().toString());
        db = new DatabaseHandler(mContext);*/
        if (!data.get(postion).getSectionName().equals("Unknown")) {
            switch (data.get(postion).getOptionIcon()) {
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
        }
        sectionName = vh.sectionName;
        ivOption = vh.ivOption;
        ivSection = vh.ivSection;

        final RelativeLayout tRl = vh.rl_root;
        vh.ivSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tRl.setBackgroundColor(Color.GRAY);
                String itemName = "";
                db = new DatabaseHandler(mContext);
                int secId = db.getSectionId(sectionName.getText().toString());
                itemName = ActivityManageSections.itemNameUseInAdapter;
                if (itemName != null && !itemName.isEmpty()) {
                    Toast.makeText(mContext, itemName + " item assign to " + sectionName.getText().toString(), Toast.LENGTH_LONG).show();
                    db = new DatabaseHandler(mContext);
                    db.getSectionData();
                    db.secAssignToItem(itemName, secId);
                }
                if (secId > 10) {
                     //prefs.edit().remove(unknownSection).commit();
                    prefs = mContext.getSharedPreferences(MyPREFERENCES, mContext.MODE_PRIVATE);
                    editor = prefs.edit();
                    editor.putString(unknownSection, secId + "");
                    editor.commit();
                    iconChangeDialog();
//                    Toast.makeText(mContext, "restoredText " + restoredText, Toast.LENGTH_LONG).show();
                }
                prefs = mContext.getSharedPreferences(MyPREFERENCES, mContext.MODE_PRIVATE);
                String restoredText = prefs.getString(unknownSection, null);

                if (restoredText != null && !restoredText.isEmpty()) {
                    db = new DatabaseHandler(mContext);
                    String icon = db.getSectionIcon(sectionName.getText().toString());
                    //db.secAssignToItem(itemName, secId);
                    db.secAssignIcon(icon, Integer.parseInt(restoredText));
                    Toast.makeText(mContext, "icon " + icon, Toast.LENGTH_LONG).show();
                    //prefs.edit().remove(unknownSection).commit();
                }
                //Toast.makeText(mContext, "section name " + sectionName.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        return vi;
    }

    public class ViewHolder {
        TextView sectionName;
        ImageView ivOption, ivSection;
        RelativeLayout rl_root;
    }

    @Override
    public void swapItems(int positionOne, int positionTwo) {
        Log.d("Postion1:" + positionOne, "Postion2: " + positionTwo);
        Object temp = data.get(positionOne);
        data.set(positionOne, data.get(positionTwo));
        data.set(positionTwo, (ManageSectionModel) temp);

        // data.set(positionOne,data.get(positionTwo).getOrderNo());
        notifyDataSetChanged();

    }

    public void iconChangeDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(mContext);
        myAlertDialog.setMessage("if you change user define section icon" +
                " click assigning icon");
        myAlertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // do something when the Cancel button is clicked
            }
        });
        myAlertDialog.show();
    }
}
