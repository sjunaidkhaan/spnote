package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class ShopParentModelMerger {

    int shop_par_id;
    String shop_par_section_icon_name;
    String shop_pa_section_name;
    boolean isClick;
    int shop_chil_id;

    public int getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(int checkBox) {
        this.checkBox = checkBox;
    }

    int checkBox;

    public boolean isParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    boolean isParent = true;

    public int getShop_chil_id() {
        return shop_chil_id;
    }

    public void setShop_chil_id(int shop_chil_id) {
        this.shop_chil_id = shop_chil_id;
    }

    public int getShop_selec_icon() {
        return shop_selec_icon;
    }

    public void setShop_selec_icon(int shop_selec_icon) {
        this.shop_selec_icon = shop_selec_icon;
    }

    public String getShop_chil_item_name() {
        return shop_chil_item_name;
    }

    public void setShop_chil_item_name(String shop_chil_item_name) {
        this.shop_chil_item_name = shop_chil_item_name;
    }

    public String getShop_chil_quantity() {
        return shop_chil_quantity;
    }

    public void setShop_chil_quantity(String shop_chil_quantity) {
        this.shop_chil_quantity = shop_chil_quantity;
    }

    int shop_selec_icon;
    String shop_chil_item_name;
    String shop_chil_quantity;


    public ShopParentModelMerger() {

    }
    public boolean isClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }
    public ShopParentModelMerger(int shop_par_id, String shop_par_section_icon_name, String shop_pa_section_name) {
        this.shop_par_id = shop_par_id;
        this.shop_par_section_icon_name = shop_par_section_icon_name;
        this.shop_pa_section_name = shop_pa_section_name;
        this.isClick = false;
    }
    public ShopParentModelMerger(String shop_par_section_icon_name, String shop_pa_section_name) {
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

}
