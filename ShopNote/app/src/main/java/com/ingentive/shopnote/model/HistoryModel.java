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


}
