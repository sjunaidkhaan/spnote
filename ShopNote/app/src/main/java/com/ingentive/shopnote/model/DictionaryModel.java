package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class DictionaryModel {

    public void setDicId(int dicId) {
        this.dicId = dicId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    int dicId;
    int sectionId;
    String itemName;

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
