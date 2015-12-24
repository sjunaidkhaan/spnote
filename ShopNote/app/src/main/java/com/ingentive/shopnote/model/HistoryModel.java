package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class HistoryModel {

    int historyId;
    String datePaurchased;
    String itemName;
    String quantity;

    public HistoryModel() {

    }
    public HistoryModel(int historyId,String datePaurchased,String itemName,String quantity) {
        this.historyId = historyId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.datePaurchased = datePaurchased;
    }
    public HistoryModel(String datePaurchased,String itemName,String quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.datePaurchased = datePaurchased;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setDatePaurchased(String datePaurchased) {
        this.datePaurchased = datePaurchased;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDatePaurchased() {
        return datePaurchased;
    }

    public int getHistoryId() {
        return historyId;
    }

    public String getQuantity() {
        return quantity;
    }


}
