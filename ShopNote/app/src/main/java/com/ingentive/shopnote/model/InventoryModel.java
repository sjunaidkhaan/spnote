package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class InventoryModel {


    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setListNo(int listNo) {
        this.listNo = listNo;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

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
