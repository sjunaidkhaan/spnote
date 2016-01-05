package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class HistoryModel {

    int historyId;
    String datePurchased;
    String itemName;
    String quantity;

    public HistoryModel() {

    }
    public HistoryModel(int historyId,String datePurchased,String itemName,String quantity) {
        this.historyId = historyId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.datePurchased = datePurchased;
    }
    public HistoryModel(String datePurchased,String itemName,String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.datePurchased = datePurchased;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public int getHistoryId() {
        return historyId;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
