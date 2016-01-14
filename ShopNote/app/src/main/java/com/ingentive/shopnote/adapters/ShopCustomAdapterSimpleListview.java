package com.ingentive.shopnote.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingentive.shopnote.DatabaseHandler;
import com.ingentive.shopnote.R;
import com.ingentive.shopnote.model.CurrentListModel;
import com.ingentive.shopnote.model.ShopParentModel;
import com.ingentive.shopnote.model.ShopParentModelMerger;
import com.nhaarman.listviewanimations.util.Swappable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 1/6/2016.
 */
public class ShopCustomAdapterSimpleListview extends BaseAdapter implements Swappable {

    private List<ShopParentModelMerger> data;
    private List<ShopParentModel> oData;
    public Context mContext;
    private static LayoutInflater inflater = null;
    public DatabaseHandler db;
    public ArrayList<Integer> ignoreList = new ArrayList<>();

    public ShopCustomAdapterSimpleListview(Context context, List<ShopParentModelMerger> data,List<ShopParentModel> oData ) {
        this.data = data;
        this.oData = oData;
        this.mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).hashCode();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderParent vhp = new ViewHolderParent();

        final ImageView ivArrow;

        if ( convertView == null){
            convertView = inflater.inflate(R.layout.custom_row_shop_parent_merge,null);
            vhp.tvShopSecNamePar = (TextView) convertView.findViewById(R.id.tv_section_name_par);
            vhp.ivShopSecIcon = (ImageView) convertView.findViewById(R.id.iv_section_shop_par);
            vhp.ivArrow = (ImageView) convertView.findViewById(R.id.iv_grab_shop_par);
            vhp.ivShopChilCheckBox = (ImageView) convertView.findViewById(R.id.iv_checkbox_shop_child);
            vhp.ivHamburger = (ImageView)convertView.findViewById(R.id.iv_grab_shop_ex);
            vhp.tvShopChilItemName = (TextView)convertView.findViewById(R.id.tv_section_name_child);

            int id = convertView.generateViewId();
            convertView.setId(id);
            convertView.setTag(vhp);

        }else{
            vhp = (ViewHolderParent)convertView.getTag();
        }


        if ( data.get(position).isParent()){
            vhp.ivArrow.setVisibility(View.VISIBLE);
            vhp.ivShopSecIcon.setVisibility(View.VISIBLE);
            vhp.ivHamburger.setVisibility(View.VISIBLE);
            vhp.tvShopSecNamePar.setVisibility(View.VISIBLE);
            vhp.tvShopChilItemName.setVisibility(View.GONE);
            vhp.ivShopChilCheckBox.setVisibility(View.GONE);
            if (data.get(position).isClick()){
                vhp.ivArrow.setBackgroundResource(R.drawable.minimize);
            }else{
                vhp.ivArrow.setBackgroundResource(R.drawable.maximize);
            }

            vhp.tvShopSecNamePar.setText(data.get(position).getShopPaSectionName().toString());



            switch (data.get(position).getShopPaSectionIcon().toString()) {
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

            ivArrow = vhp.ivArrow;
            vhp.ivArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //tvShopSecNamePar.setTextColor(Color.GRAY);
                    if (!data.get(position).isClick()) {
                        data.get(position).setIsClick(true);
                        ivArrow.setBackgroundResource(R.drawable.minimize);
                        for( int i = 0; i < ignoreList.size(); ++i ){
                            if ( data.get(position).getShopPaId() == ignoreList.get(i) ){
                                ignoreList.remove(i);
                                data = getData(ignoreList);
                                notifyDataSetChanged();
                            }
                        }
                    } else {
                        data.get(position).setIsClick(false);
                        ivArrow.setBackgroundResource(R.drawable.maximize);
                        //remove item of this parent
                        ignoreList.add(data.get(position).getShopPaId());
                        data = getData(ignoreList);
                        notifyDataSetChanged();

                    }
                }
            });
        }else{
            vhp.ivArrow.setVisibility(View.GONE);
            vhp.ivShopSecIcon.setVisibility(View.GONE);
            vhp.ivHamburger.setVisibility(View.GONE);
            vhp.tvShopSecNamePar.setVisibility(View.GONE);
            vhp.ivShopChilCheckBox.setVisibility(View.VISIBLE);
            vhp.tvShopChilItemName.setVisibility(View.VISIBLE);

            vhp.tvShopChilItemName.setText(data.get(position).getShop_chil_quantity() + " " + data.get(position).getShop_chil_item_name());
            db = new DatabaseHandler(mContext);
            int isChecked = db.isChecked(data.get(position).getShop_chil_item_name());
            db.close();
            if (isChecked == 1) {
                vhp.ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
                vhp.tvShopChilItemName.setTextColor(Color.GRAY);
            } else {
                vhp.ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_unchecked);
                vhp.tvShopChilItemName.setTextColor(Color.BLACK);
            }

            final ImageView ivShopChilCheckBox = vhp.ivShopChilCheckBox;
            final TextView tvShopChilItemName = vhp.tvShopChilItemName;
            vhp.ivShopChilCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db = new DatabaseHandler(mContext);
                    int isChecked = db.isChecked(data.get(position).getShop_chil_item_name());
                    if (isChecked == 1) {
                        CurrentListModel unCheck = new CurrentListModel();
                        unCheck.setCurrListId(data.get(position).getShop_chil_id());
                        unCheck.setChecked(0);
                        unCheck.setItemName(data.get(position).getShop_chil_item_name());
                        db.updateCheckItem(unCheck);
                        ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_unchecked);
                        tvShopChilItemName.setTextColor(Color.BLACK);
                        data.get(position).setCheckBox(0);


                    } else {
                        CurrentListModel currCheck = new CurrentListModel();
                        currCheck.setChecked(1);
                        currCheck.setItemName(data.get(position).getShop_chil_item_name());
                        currCheck.setCurrListId(data.get(position).getShop_chil_id());
                        ivShopChilCheckBox.setBackgroundResource(R.drawable.checkbox_checked);
                        tvShopChilItemName.setTextColor(Color.GRAY);
                        db.updateCheckItem(currCheck);
                        data.get(position).setCheckBox(1);
                    }
                    db.close();
//                if (shopParent.get(groupPosition).getArrayChildren().get(childPosition).getCheckBox() == 0) {
//                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(1);
//                } else {
//                    shopParent.get(groupPosition).getArrayChildren().get(childPosition).setCheckBox(0);
//                }
                    ////
                    ///jk
//                    int counter = 0;
//                    for (int i = 0; i < shopParent.get(groupPosition).getArrayChildren().size(); ++i) {
//                        if (shopParent.get(groupPosition).getArrayChildren().get(i).getCheckBox() == 1) {
//                            counter++;
//                        }
//                    }
//                    if (counter == shopParent.get(groupPosition).getArrayChildren().size()) {
//                        //Toast.makeText(mContext, "due to child", Toast.LENGTH_LONG).show();
//                        (shopParent.get(groupPosition).getView().tvShopSecNamePar).setTextColor(Color.GRAY);
//                    } else {
//                        (shopParent.get(groupPosition).getView().tvShopSecNamePar).setTextColor(Color.BLACK);
//                    }
                    ///
                }
            });

        }


        return convertView;
    }

    @Override
    public void swapItems(int to, int from) {
//        Log.d("P1: ", to+"");
//        Log.d("P2: ", from+"");
//        Log.d("from:" + from + "|||" + data.get(from).getShopPaId(), "to:" + to+ "|||" + data.get(to).getShopPaId());


        Object temp = this.data.get(to);
        this.data.set(to, this.data.get(from));
        this.data.set(from, (ShopParentModelMerger) temp);


        notifyDataSetChanged();
    }


    public class ViewHolderParent
    {
        TextView tvShopSecNamePar;
        ImageView ivShopSecIcon;
        ImageView ivArrow;
        TextView tvShopChilItemName,tvShopChilQuantity;
        ImageView ivShopChilCheckBox;
        ImageView ivHamburger;
    }

    public List<ShopParentModelMerger> getData(ArrayList toIgnore){

        List<ShopParentModelMerger> dataTemp = new ArrayList<>();

        for ( int i = 0; i <this.oData.size(); ++i){

                if ( ignoreList.contains(this.oData.get(i).getShopPaId()) ){
                    ShopParentModelMerger tParent = new ShopParentModelMerger();
                    tParent.setIsParent(true);
                    tParent.setIsClick(false);
                    tParent.setShopPaId(this.oData.get(i).getShopPaId());
                    tParent.setShopPaSectionName(this.oData.get(i).getShopPaSectionName().toString());
                    tParent.setShopPaSectionIcon(this.oData.get(i).getShopPaSectionIcon().toString());
                    dataTemp.add(tParent);
                }else{

                    ShopParentModelMerger tParent = new ShopParentModelMerger();
                    tParent.setIsParent(true);
                    tParent.setIsClick(true);
                    tParent.setShopPaId(this.oData.get(i).getShopPaId());
                    tParent.setShopPaSectionName(this.oData.get(i).getShopPaSectionName().toString());
                    tParent.setShopPaSectionIcon(this.oData.get(i).getShopPaSectionIcon().toString());

                    dataTemp.add(tParent);

                    for ( int j = 0; j < oData.get(i).getArrayChildren().size(); ++j){

                        ShopParentModelMerger tChild = new ShopParentModelMerger();
                        tChild.setIsParent(false);
                        tChild.setIsClick(false);
                        tChild.setShopPaId(oData.get(i).getShopPaId());
                        tChild.setShopPaSectionName(oData.get(i).getShopPaSectionName().toString());
                        tChild.setShopPaSectionIcon(oData.get(i).getShopPaSectionIcon().toString());
                        tChild.setShop_chil_id(oData.get(i).getArrayChildren().get(j).getShopChId());
                        tChild.setShop_chil_item_name(oData.get(i).getArrayChildren().get(j).getShopChItemName());
                        tChild.setCheckBox(oData.get(i).getArrayChildren().get(j).getCheckBox());
                        tChild.setShop_chil_quantity(oData.get(i).getArrayChildren().get(j).getShopChQuantity());
                        tChild.setShop_selec_icon(oData.get(i).getArrayChildren().get(j).getShopChSectionId());

                        dataTemp.add(tChild);

                    }


                }
        }
        return dataTemp;

    }
    @Override
    public boolean hasStableIds() {
        return true;
    }

}