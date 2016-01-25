package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    private List<ManageSectionModel> data;
    private int res;
    private Context mContext;
    private static LayoutInflater inflater = null;

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

        if (sectionName.getText().toString().equals(ActivityManageSections.sectionName) && ActivityManageSections.itemNameUseInAdapter != null && !ActivityManageSections.itemNameUseInAdapter.isEmpty()) {

            String sectionImage = DatabaseHandler.getInstance(mContext).getSectionIconWithItemName(ActivityManageSections.itemNameUseInAdapter);
            if (sectionImage.equals("unknown.png")) {
                tRl.setBackgroundColor(Color.TRANSPARENT);
            } else {
                tRl.setBackgroundColor(Color.GRAY);
            }
        } else {
            tRl.setBackgroundColor(Color.TRANSPARENT);
        }
        vh.sectionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemName = "";
                int secId = DatabaseHandler.getInstance(mContext).getSectionId(sectionName.getText().toString());
                itemName = ActivityManageSections.itemNameUseInAdapter;
                if (itemName != null && !itemName.isEmpty()) {
                    tRl.setBackgroundColor(Color.GRAY);
                    DatabaseHandler.getInstance(mContext).getSectionData();
                    DatabaseHandler.getInstance(mContext).secAssignToItem(itemName, secId);
                    Handler handler = new Handler();
                    Runnable r = new Runnable() {
                        public void run() {
                            ((Activity) mContext).finish();
                        }
                    };
                    handler.postDelayed(r, 2000);
                }
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
        Object temp = data.get(positionOne);
        data.set(positionOne, data.get(positionTwo));
        data.set(positionTwo, (ManageSectionModel) temp);
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
