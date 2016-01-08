package com.ingentive.shopnote.model;

import android.view.View;

import java.util.List;

/**
 * Created by PC on 12/23/2015.
 */
public class ShopParentModel {

    int shop_par_id;
    String shop_par_section_icon_name;
    String shop_pa_section_name;
    private List<ShopChildModel> mShopChildrenList;
    boolean isClick = false;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    View view;

    public ShopParentModel() {

    }
    public boolean isClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }
    public ShopParentModel(int shop_par_id, String shop_par_section_icon_name,String shop_pa_section_name) {
        this.shop_par_id = shop_par_id;
        this.shop_par_section_icon_name = shop_par_section_icon_name;
        this.shop_pa_section_name = shop_pa_section_name;
        this.isClick = false;
    }
    public ShopParentModel(String shop_par_section_icon_name,String shop_pa_section_name) {
        this.shop_par_section_icon_name = shop_par_section_icon_name;
        this.shop_pa_section_name = shop_pa_section_name;
    }
    public String getShopPaSectionIcon() {
        return shop_par_section_icon_name;
    }
    public void setShopPaSectionIcon(String shop_par_section_icon_name) {
        this.shop_par_section_icon_name = shop_par_section_icon_name;
    }
    public String getShopPaSectionName() {
        return shop_pa_section_name;
    }

    public int getShopPaId() {
        return shop_par_id;
    }
    public void setShopPaId(int shopId) {
        this.shop_par_id = shopId;
    }

    public void setShopPaSectionName(String sectionName) {
        this.shop_pa_section_name = sectionName;
    }
    public List<ShopChildModel> getArrayChildren() {
        return mShopChildrenList;
    }

    public void setArrayChildren(List<ShopChildModel> arrayChildren) {
        mShopChildrenList = arrayChildren;
    }

}
