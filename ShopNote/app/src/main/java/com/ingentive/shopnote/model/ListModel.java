package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class ListModel {
    int listId;
    int iconOption;
    int iconFavorit;
    int iconSection;
    String itemName;

    public ListModel() {

    }

    public ListModel(int listId, int iconOption, int iconFavorit, int iconSection ,String itemName) {
        this.listId = listId;
        this.iconOption = iconOption;
        this.iconFavorit = iconFavorit;
        this.iconSection = iconSection;
        this.itemName = itemName;
    }

    public ListModel(int iconOption, int iconFavorit, int iconSection ,String itemName) {
        this.iconOption = iconOption;
        this.iconFavorit = iconFavorit;
        this.iconSection = iconSection;
        this.itemName = itemName;
    }

    public int getIconOption() {
        return iconOption;
    }

    public void setIconOption(int iconOption) {
        this.iconOption = iconOption;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getIconFavorit() {
        return iconFavorit;
    }

    public void setIconFavorit(int iconFavorit) {
        this.iconFavorit = iconFavorit;
    }

    public int getIconSection() {
        return iconSection;
    }

    public void setIconSection(int iconSection) {
        this.iconSection = iconSection;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}