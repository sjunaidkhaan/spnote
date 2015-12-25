package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class FavoritListModel {

    int favListId;
    String itemName;

    public FavoritListModel() {

    }
    public FavoritListModel(int favListId,String itemName) {
        this.favListId = favListId;
        this.itemName = itemName;
    }
    public FavoritListModel(String itemName) {
        this.itemName = itemName;
    }

    public int getFavListId() {
        return favListId;
    }

    public String getItemName() {
        return itemName;
    }

}
