package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class DictionaryModel {

    int dicId;
    int sectionId;
    String itemName;

    public int getFavItem() {
        return favItem;
    }

    public void setFavItem(int favItem) {
        this.favItem = favItem;
    }

    public int getDicId() {
        return dicId;
    }

    public int getHistoryItem() {
        return historyItem;
    }

    public void setHistoryItem(int historyItem) {
        this.historyItem = historyItem;
    }

    int favItem;
    int historyItem;

    public DictionaryModel() {

    }
    public DictionaryModel(String itemName,int sectionId) {
        this.itemName = itemName;
        this.sectionId = sectionId;
    }

    public DictionaryModel(int dicId, String itemName,int sectionId) {
        this.dicId = dicId;
        this.itemName = itemName;
        this.sectionId = sectionId;
    }
    public void setDicId(int dicId) {
        this.dicId = dicId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getDictionaryId() {
        return dicId;
    }

    public String getItemName() {
        return itemName;
    }
}
