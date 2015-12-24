package com.ingentive.shopnote;

/**
 * Created by PC on 12/22/2015.
 */
public class ItemModel {

    int itemId;
    int sectionId;
    String itemName;

    public ItemModel() {

    }

    public ItemModel(int itemId, String itemName,int sectionId) {
        this.itemId = itemId;
        this.sectionId = sectionId;
        this.itemName = itemName;
    }

    public ItemModel(String itemName,int sectionId) {
        this.itemName = itemName;
        this.sectionId = sectionId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
