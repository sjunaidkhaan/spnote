package com.ingentive.shopnote.model;

public class ListAddBasicModel {

    int id;
    String itemName;

    public ListAddBasicModel() {

    }

    public ListAddBasicModel(int id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
