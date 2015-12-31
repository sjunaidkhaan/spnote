package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class DictionaryModel {

    int dicId;
    int sectionId;
    int favItem;
    int favIcon;
    int historyIcon;
    int historyItem;
    String itemName;

    public int getFavIcon() {
        return favIcon;
    }

    public void setFavIcon(int favIcon) {
        this.favIcon = favIcon;
    }

    public int getHistoryIcon() {
        return historyIcon;
    }

    public void setHistoryIcon(int historyIcon) {
        this.historyIcon = historyIcon;
    }


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

    public DictionaryModel() {

    }
    public DictionaryModel(String itemName,int sectionId) {
        this.itemName = itemName;
        this.sectionId = sectionId;
    }
    public DictionaryModel(String itemName,int favIcon,int historyIcon) {
        this.itemName = itemName;
        this.favIcon = favIcon;
        this.historyIcon = historyIcon;
    }
    public DictionaryModel(String itemName,int favItem,int historyItem,int favIcon,int historyIcon) {
        this.itemName = itemName;
        this.favItem = favItem;
        this.historyItem = historyItem;
        this.favIcon = favIcon;
        this.historyIcon = historyIcon;
    }

    public DictionaryModel(int dicId,String itemName,int favItem,int historyItem,int favIcon,int historyIcon) {
        this.dicId = dicId;
        this.itemName = itemName;
        this.favItem = favItem;
        this.historyItem = historyItem;
        this.favIcon = favIcon;
        this.historyIcon = historyIcon;
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

    public String getItemName() {
        return itemName;
    }
}
