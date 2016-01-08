package com.ingentive.shopnote.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.ShopParentModel;

import java.util.List;

/**
 * Created by PC on 1/6/2016.
 */
public class ShopCustomAdapter extends BaseExpandableListAdapter {

    private List<ShopParentModel> shopParent;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;
    public ViewGroup globe;

    public ShopCustomAdapter(Context context, List<ShopParentModel> parent) {
        this.shopParent = parent;
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return shopParent.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return shopParent.get(i).getArrayChildren().size();
    }

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return shopParent.get(i).getShopPaSectionName().toString();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return shopParent.get(i).getArrayChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(final int groupPosition, boolean b, View rowView, final ViewGroup viewGroup) {

        globe = viewGroup;

        View vi = rowView;
        if (vi == null) {
            vi = inflater.inflate(R.layout.custom_row_shop_parent, viewGroup, false);
        }

        final TextView tvShopSecNamePar = (TextView) vi.findViewById(R.id.tv_section_name_par);
        tvShopSecNamePar.setText(shopParent.get(groupPosition).getShopPaSectionName().toString());
        final ImageView ivShopSecIcon = (ImageView) vi.findViewById(R.id.iv_section_shop_par);
        final ImageView ivArrow = (ImageView) vi.findViewById(R.id.iv_grab_shop_par);
        switch (shopParent.get(groupPosition).getShopPaSectionIcon().toString()) {
            case "clothing.png":
                ivShopSecIcon.setBackgroundResource(R.drawable.clothing);
                break;
            case "house.png":
                ivShopSecIcon.setImageResource(R.drawable.house);
                break;
            case "pharmacy.png":
                ivShopSecIcon.setImageResource(R.drawable.pharmacy);
                break;
            case "produce.png":
                ivShopSecIcon.setImageResource(R.drawable.produce);
                break;
            case "bakery.png":
                ivShopSecIcon.setImageResource(R.drawable.bakery);
                break;
            case "dry_goods.png":
                ivShopSecIcon.setImageResource(R.drawable.dry_goods);
                break;
            case "beverages.png":
                ivShopSecIcon.setImageResource(R.drawable.beverages);
                break;
            case "freezer.png":
                ivShopSecIcon.setImageResource(R.drawable.freezer);
                break;
            case "dairy.png":
                ivShopSecIcon.setImageResource(R.drawable.dairy);
                break;
            case "meat.png":
                ivShopSecIcon.setImageResource(R.drawable.meat);
                break;
            default:
                ivShopSecIcon.setImageResource(R.drawable.unknown);
                break;
        }
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tvShopSecNamePar.setTextColor(Color.GRAY);
                if (!shopParent.get(groupPosition).isClick()) {
                    shopParent.get(groupPosition).setIsClick(true);
                    ExpandableListView mExpandableListView = (ExpandableListView) viewGroup;
                    mExpandableListView.expandGroup(groupPosition);
                    ivArrow.setBackgroundResource(R.drawable.minimize);
                } else {
                    shopParent.get(groupPosition).setIsClick(false);
                    ExpandableListView mExpandableListView = (ExpandableListView) viewGroup;
                    mExpandableListView.collapseGroup(groupPosition);
                    ivArrow.setBackgroundResource(R.drawable.maximize);
                }
            }
        });

        //jk
        int counter = 0;
        for ( int i = 0; i < shopParent.get(groupPosition).getArrayChildren().size(); ++i ){
            if (shopParent.get(groupPosition).getArrayChildren().get(i).getCheckBox()==1 ){
                counter++;
            }
        }

        if ( counter == shopParent.get(groupPosition).getArrayChildren().size() ){
            tvShopSecNamePar.setTextColor(Color.GRAY);
        }
        //

        shopParent.get(groupPosition).setView(vi);
        return vi;
    }
    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, final ViewGroup viewGroup) {

        View childView = view;
        if (childView == null) {
            childView = inflater.inflate(R.layout.custom_row_shop_child, null);
        }

        final TextView tvShopChilItemName = (TextView) childView.findViewById(R.id.tv_item_name_shop_child);
//        final String quantity = shopParent.get(groupPosition).getArrayChildren().get(childPosition).getShopChQuantity().toString();
        final String name = shopParent.get(groupPosition).getArrayChildren().get(childPosition).getShopChItemName().toString();
        /*if(!quantity.equals(null)&&!quantity.equals("1"))
            tvShopChilItemName.setText(quantity+" "+name);
        else*/
        tvShopChilItemName.setText(name);
        final ImageView ivShopChilCheckBox = (ImageView) childView.findViewById(R.id.iv_checkbox_shop_child);
        db = new DatabaseHandler(mContext);
        int isChecked = db.isChecked(name);
        if (isChecked == 1) {
            tvShopChilItemName.setTextColor(Color.GRAY);
            ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
            //Toast.makeText(mContext, "if "+shopParent.get(groupPosition).getArrayChildren().get(childPosition).CheckBox(), Toast.LENGTH_SHORT).show();
        } else {
            ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_unchecked);
            tvShopChilItemName.setTextColor(Color.BLACK);
            //Toast.makeText(mContext, "else "+shopParent.get(groupPosition).getArrayChildren().get(childPosition).CheckBox(), Toast.LENGTH_SHORT).show();
        }
        ivShopChilCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHandler(mContext);
                int isChecked = db.isChecked(name);
                if (isChecked == 1) {
                    CurrentListModel unCheck = new CurrentListModel();
                    unCheck.setCurrListId(shopParent.get(groupPosition).getArrayChildren().get(childPosition).getShopChId());
                    unCheck.setChecked(0);
                    unCheck.setItemName(name);
                    db.updateCheckItem(unCheck);
                    ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_unchecked);
                    tvShopChilItemName.setTextColor(Color.BLACK);


                } else {
                    CurrentListModel currCheck = new CurrentListModel();
                    currCheck.setChecked(1);
                    currCheck.setItemName(name);
                    currCheck.setCurrListId(shopParent.get(groupPosition).getArrayChildren().get(childPosition).getShopChId());
                    ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
                    tvShopChilItemName.setTextColor(Color.GRAY);
                    db.updateCheckItem(currCheck);
                }

                if (shopParent.get(groupPosition).getArrayChildren().get(childPosition).getCheckBox() == 0) {
                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(1);
                } else {
                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(0);
                }
                ////
                ///jk
                int counter = 0;
                for (int i = 0; i < shopParent.get(groupPosition).getArrayChildren().size(); ++i) {
                    if (shopParent.get(groupPosition).getArrayChildren().get(i).getCheckBox() == 1) {
                        counter++;
                    }
                }
                View pp = ((ExpandableListView)globe).getChildAt(groupPosition);
                if (counter == shopParent.get(groupPosition).getArrayChildren().size()) {
                    ((TextView) (shopParent.get(groupPosition).getView().findViewById(R.id.tv_section_name_par))).setTextColor(Color.GRAY);
                } else {
                    ((TextView) (shopParent.get(groupPosition).getView().findViewById(R.id.tv_section_name_par))).setTextColor(Color.BLACK);
                }
                ///
            }
        });
        return childView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }
}