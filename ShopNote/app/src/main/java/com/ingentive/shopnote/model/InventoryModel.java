package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class InventoryModel {


    int inventoryId;
    int listNo;
    String listName;

    public InventoryModel() {

    }
    public InventoryModel(int inventoryId,int listNo,String listName) {
        this.inventoryId = inventoryId;
        this.listNo = listNo;
        this.listName = listName;
    }
    public InventoryModel(int listNo,String listName) {
        this.listNo = listNo;
        this.listName = listName;
    }
    public int getInventoryId() {
        return inventoryId;
    }

    public int getListNo() {
        return listNo;
    }

    public String getListName() {
        return listName;
    }



}
