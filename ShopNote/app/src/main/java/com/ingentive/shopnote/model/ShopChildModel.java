package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class ShopChildModel {

    int shop_chil_id;
    int shop_selec_icon;
    String shop_chil_item_name;
    String shop_chil_quantity;

    public ShopChildModel() {

    }

    public ShopChildModel(int shop_chil_id,int shop_selec_icon, String itemName,String quantity) {
        this.shop_chil_id = shop_chil_id;
        this.shop_selec_icon = shop_selec_icon;
        this.shop_chil_item_name = itemName;
        this.shop_chil_quantity = quantity;
    }
    public ShopChildModel(int shop_selec_icon, String itemName,String quantity) {
        this.shop_selec_icon = shop_selec_icon;
        this.shop_chil_item_name = itemName;
        this.shop_chil_quantity = quantity;
    }
    public int getShopChId() {
        return shop_chil_id;
    }

    public void setShopChId(int shop_chil_id) {
        this.shop_chil_id = shop_chil_id;
    }
    public int getShopChSectionId() {
        return shop_selec_icon;
    }

    public void setShopChSectionId(int shop_selec_icon) {
        this.shop_selec_icon = shop_selec_icon;
    }

    public String getShopChItemName() {
        return shop_chil_item_name;
    }

    public void setShopChItemName(String shop_chil_item_name) {
        this.shop_chil_item_name = shop_chil_item_name;
    }
    public String getShopChQuantity() {
        return shop_chil_quantity;
    }

    public void setShopChQuantity(String shop_chil_quantity) {
        this.shop_chil_quantity = shop_chil_quantity;
    }

}
