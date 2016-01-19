package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
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
            vh.rl_root = (RelativeLayout) vi.findViewById(R.id.custom_row_list);
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
        if (data.get(postion).getManageSectionId() <= 10) {
            switch (data.get(postion).getSectionName()) {
                case "Clothing":
                    vh.ivSection.setBackgroundResource(R.drawable.clothing);
                    break;
                case "Household and Specialty":
                    vh.ivSection.setBackgroundResource(R.drawable.house);
                    break;
                case "Pharmacy":
                    vh.ivSection.setBackgroundResource(R.drawable.pharmacy);
                    break;
                case "Produce":
                    vh.ivSection.setBackgroundResource(R.drawable.produce);
                    break;
                case "Bakery":
                    vh.ivSection.setBackgroundResource(R.drawable.bakery);
                    break;
                case "Packaged Foodstuff":
                    vh.ivSection.setBackgroundResource(R.drawable.dry_goods);
                    break;
                case "Beverages":
                    vh.ivSection.setBackgroundResource(R.drawable.beverages);
                    break;
                case "Frozen Food":
                    vh.ivSection.setBackgroundResource(R.drawable.freezer);
                    break;
                case "Dairy and Refridgerated":
                    vh.ivSection.setBackgroundResource(R.drawable.dairy);
                    break;
                case "Meat and Seafood":
                    vh.ivSection.setBackgroundResource(R.drawable.meat);
                    break;
            }
        } else {
            if (!data.get(postion).getSectionName().equals("Unknown")) {
                switch (data.get(postion).getSectionName().toLowerCase().charAt(0)) {
                    case 'a':
                        vh.ivSection.setBackgroundResource(R.drawable.a);
                        break;
                    case 'b':
                        vh.ivSection.setBackgroundResource(R.drawable.b);
                        break;
                    case 'c':
                        vh.ivSection.setBackgroundResource(R.drawable.c);
                        break;
                    case 'd':
                        vh.ivSection.setBackgroundResource(R.drawable.d);
                        break;
                    case 'e':
                        vh.ivSection.setBackgroundResource(R.drawable.e);
                        break;
                    case 'f':
                        vh.ivSection.setBackgroundResource(R.drawable.f);
                        break;
                    case 'g':
                        vh.ivSection.setBackgroundResource(R.drawable.g);
                        break;
                    case 'h':
                        vh.ivSection.setBackgroundResource(R.drawable.h);
                        break;
                    case 'i':
                        vh.ivSection.setBackgroundResource(R.drawable.i);
                        break;
                    case 'j':
                        vh.ivSection.setBackgroundResource(R.drawable.j);
                        break;
                    case 'k':
                        vh.ivSection.setBackgroundResource(R.drawable.k);
                        break;
                    case 'l':
                        vh.ivSection.setBackgroundResource(R.drawable.l);
                        break;
                    case 'm':
                        vh.ivSection.setBackgroundResource(R.drawable.m);
                        break;
                    case 'n':
                        vh.ivSection.setBackgroundResource(R.drawable.n);
                        break;
                    case 'o':
                        vh.ivSection.setBackgroundResource(R.drawable.o);
                        break;
                    case 'p':
                        vh.ivSection.setBackgroundResource(R.drawable.p);
                        break;
                    case 'q':
                        vh.ivSection.setBackgroundResource(R.drawable.q);
                        break;
                    case 'r':
                        vh.ivSection.setBackgroundResource(R.drawable.r);
                        break;
                    case 's':
                        vh.ivSection.setBackgroundResource(R.drawable.s);
                        break;
                    case 't':
                        vh.ivSection.setBackgroundResource(R.drawable.t);
                        break;
                    case 'u':
                        vh.ivSection.setBackgroundResource(R.drawable.u);
                        break;
                    case 'v':
                        vh.ivSection.setBackgroundResource(R.drawable.v);
                        break;
                    case 'w':
                        vh.ivSection.setBackgroundResource(R.drawable.w);
                        break;
                    case 'x':
                        vh.ivSection.setBackgroundResource(R.drawable.x);
                        break;
                    case 'y':
                        vh.ivSection.setBackgroundResource(R.drawable.y);
                        break;
                    case 'z':
                        vh.ivSection.setBackgroundResource(R.drawable.z);
                        break;
                    default:
                        break;
                }
            }
        }


        sectionName = vh.sectionName;
        ivOption = vh.ivOption;
        ivSection = vh.ivSection;

        final RelativeLayout tRl = vh.rl_root;
//        Toast.makeText(mContext, " sectionname " + sectionName, Toast.LENGTH_LONG).show();
        if (sectionName.getText().toString().equals(ActivityManageSections.sectionName)) {
            tRl.setBackgroundColor(Color.GRAY);
            //Toast.makeText(mContext, " sectionname " + sectionName.getText().toString(), Toast.LENGTH_LONG).show();
        } else {
            tRl.setBackgroundColor(Color.TRANSPARENT);
        }
        vh.sectionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "";
                db = new DatabaseHandler(mContext);
                int secId = db.getSectionId(sectionName.getText().toString());
                //Toast.makeText(mContext, "section Id " + sectionName.getText().toString(), Toast.LENGTH_LONG).show();
                itemName = ActivityManageSections.itemNameUseInAdapter;
                Toast.makeText(mContext, sectionName.getText().toString() + " Assign to  " + itemName, Toast.LENGTH_LONG).show();
                if (itemName != null && !itemName.isEmpty()) {
                    db = new DatabaseHandler(mContext);
                    db.getSectionData();
                    db.secAssignToItem(itemName, secId);
                    Toast.makeText(mContext, sectionName.getText().toString() + " Assign to  " + itemName, Toast.LENGTH_LONG).show();
                    //((Activity)mContext).finish();

                    Handler handler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            tRl.setBackgroundColor(Color.GRAY);
                            ((Activity) mContext).finish();
                        }
                    };
                    handler.postDelayed(r, 2000);
                }
//                if (secId > 10) {
//                    //prefs.edit().remove(unknownSection).commit();
//                    prefs = mContext.getSharedPreferences(MyPREFERENCES, mContext.MODE_PRIVATE);
//                    editor = prefs.edit();
//                    editor.putString(unknownSection, secId + "");
//                    editor.commit();
//                    iconChangeDialog();
////                    Toast.makeText(mContext, "restoredText " + restoredText, Toast.LENGTH_LONG).show();
//                }
//                prefs = mContext.getSharedPreferences(MyPREFERENCES, mContext.MODE_PRIVATE);
//                String restoredText = prefs.getString(unknownSection, null);
//
//                if (restoredText != null && !restoredText.isEmpty()) {
//                    db = new DatabaseHandler(mContext);
//                    String icon = db.getSectionIcon(sectionName.getText().toString());
//                    //db.secAssignToItem(itemName, secId);
//                    db.secAssignIcon(icon, Integer.parseInt(restoredText));
//                    //Toast.makeText(mContext, "icon " + icon, Toast.LENGTH_LONG).show();
//                    notifyDataSetChanged();
//                    //prefs.edit().remove(unknownSection).commit();
//                }
                //Toast.makeText(mContext, "section name " + sectionName.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
        return vi;
    }

    private void showIcon(String optionIcon) {
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
        myAlertDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // do something when the Cancel button is clicked
            }
        });
        myAlertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // do something when the Cancel button is clicked
            }
        });

        myAlertDialog.show();
    }
}
