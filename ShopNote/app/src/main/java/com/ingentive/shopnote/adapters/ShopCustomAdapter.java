package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(final int groupPosition, boolean b, View rowView, final ViewGroup viewGroup) {


        final TextView tvShopSecNamePar;
        final ImageView ivShopSecIcon;
        final ImageView ivArrow;


        View vi = rowView;
        ViewHolderParent vhp = new ViewHolderParent();

        if (vi == null) {
            vi = inflater.inflate(R.layout.custom_row_shop_parent, viewGroup, false);

            vhp.tvShopSecNamePar = (TextView) vi.findViewById(R.id.tv_section_name_par);
            vhp.ivShopSecIcon = (ImageView) vi.findViewById(R.id.iv_section_shop_par);
            vhp.ivArrow = (ImageView) vi.findViewById(R.id.iv_grab_shop_par);
            int id = vi.generateViewId();
            vi.setId(id);
            vi.setTag(vhp);

        }else{
            vhp = (ViewHolderParent)vi.getTag();
        }

        if ( b ){
            vhp.ivArrow.setBackgroundResource(R.drawable.minimize);
        }else{
            vhp.ivArrow.setBackgroundResource(R.drawable.maximize);
        }

        shopParent.get(groupPosition).setView(vhp);
        vhp.tvShopSecNamePar.setText(shopParent.get(groupPosition).getShopPaSectionName().toString());

        switch (shopParent.get(groupPosition).getShopPaSectionIcon().toString()) {
            case "clothing.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.clothing);
                break;
            case "house.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.house);
                break;
            case "pharmacy.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.pharmacy);
                break;
            case "produce.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.produce);
                break;
            case "bakery.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.bakery);
                break;
            case "dry_goods.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.dry_goods);
                break;
            case "beverages.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.beverages);
                break;
            case "freezer.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.freezer);
                break;
            case "dairy.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.dairy);
                break;
            case "meat.png":
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.meat);
                break;
            default:
                vhp.ivShopSecIcon.setBackgroundResource(R.drawable.unknown);
                break;
        }
        //jk
        int counter = 0;
        for ( int i = 0; i < shopParent.get(groupPosition).getArrayChildren().size(); ++i ){
            if (shopParent.get(groupPosition).getArrayChildren().get(i).getCheckBox()==1 ){
                counter++;
            }
        }

        if ( counter == shopParent.get(groupPosition).getArrayChildren().size() ){
            vhp.tvShopSecNamePar.setTextColor(Color.GRAY);
        }else{
            vhp.tvShopSecNamePar.setTextColor(Color.BLACK);
        }
        //


        ivArrow = vhp.ivArrow;
        vhp.ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tvShopSecNamePar.setTextColor(Color.GRAY);
                if (!shopParent.get(groupPosition).isClick()) {
                    shopParent.get(groupPosition).setIsClick(true);
                    ExpandableListView mExpandableListView = (ExpandableListView) viewGroup;
                    mExpandableListView.expandGroup(groupPosition);
                    //shopParent.get(groupPosition).getView().ivArrow.setBackgroundResource(R.drawable.minimize);
                } else {
                    shopParent.get(groupPosition).setIsClick(false);
                    ExpandableListView mExpandableListView = (ExpandableListView) viewGroup;
                    mExpandableListView.collapseGroup(groupPosition);
                    //shopParent.get(groupPosition).getView().ivArrow.setBackgroundResource(R.drawable.maximize);
                }
            }
        });

        return vi;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, final ViewGroup viewGroup) {

        View childView = view;

        ViewHolderChild vhc = new ViewHolderChild();


        if (childView == null) {
            childView = inflater.inflate(R.layout.custom_row_shop_child, null);
            vhc.tvShopChilItemName = (TextView) childView.findViewById(R.id.tv_item_name_shop_child);
            vhc.ivShopChilCheckBox = (ImageView) childView.findViewById(R.id.iv_checkbox_shop_child);
            int id = childView.generateViewId();
            childView.setId(id);
            childView.setTag(vhc);

        }else{
            vhc = (ViewHolderChild)childView.getTag();
        }


//        final String quantity = shopParent.get(groupPosition).getArrayChildren().get(childPosition).getShopChQuantity().toString();
        final String name = shopParent.get(groupPosition).getArrayChildren().get(childPosition).getShopChItemName().toString();
        /*if(!quantity.equals(null)&&!quantity.equals("1"))
            tvShopChilItemName.setText(quantity+" "+name);
        else*/
        vhc.tvShopChilItemName.setText(name);

        db = new DatabaseHandler(mContext);
        int isChecked = db.isChecked(name);
        if (isChecked == 1) {
            vhc.tvShopChilItemName.setTextColor(Color.GRAY);
            vhc.ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
            //Toast.makeText(mContext, "if "+shopParent.get(groupPosition).getArrayChildren().get(childPosition).CheckBox(), Toast.LENGTH_SHORT).show();
        } else {
            vhc.ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_unchecked);
            vhc.tvShopChilItemName.setTextColor(Color.BLACK);
            //Toast.makeText(mContext, "else "+shopParent.get(groupPosition).getArrayChildren().get(childPosition).CheckBox(), Toast.LENGTH_SHORT).show();
        }

        final ImageView ivShopChilCheckBox = vhc.ivShopChilCheckBox;
        final TextView tvShopChilItemName = vhc.tvShopChilItemName;
        vhc.ivShopChilCheckBox.setOnClickListener(new View.OnClickListener() {
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
                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(0);


                } else {
                    CurrentListModel currCheck = new CurrentListModel();
                    currCheck.setChecked(1);
                    currCheck.setItemName(name);
                    currCheck.setCurrListId(shopParent.get(groupPosition).getArrayChildren().get(childPosition).getShopChId());
                    ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
                    tvShopChilItemName.setTextColor(Color.GRAY);
                    db.updateCheckItem(currCheck);
                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(1);
                }

//                if (shopParent.get(groupPosition).getArrayChildren().get(childPosition).getCheckBox() == 0) {
//                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(1);
//                } else {
//                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(0);
//                }
                ////
                ///jk
                int counter = 0;
                for (int i = 0; i < shopParent.get(groupPosition).getArrayChildren().size(); ++i) {
                    if (shopParent.get(groupPosition).getArrayChildren().get(i).getCheckBox() == 1) {
                        counter++;
                    }
                }
                if (counter == shopParent.get(groupPosition).getArrayChildren().size()) {
                    Toast.makeText(mContext, "due to child", Toast.LENGTH_LONG).show();
                    (shopParent.get(groupPosition).getView().tvShopSecNamePar).setTextColor(Color.GRAY);
                } else {
                    (shopParent.get(groupPosition).getView().tvShopSecNamePar).setTextColor(Color.BLACK);
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


    public class ViewHolderParent
    {
        TextView tvShopSecNamePar;
        ImageView ivShopSecIcon;
        ImageView ivArrow;

    }
    public class ViewHolderChild
    {
        TextView tvShopChilItemName;
        ImageView ivShopChilCheckBox;
    }
}