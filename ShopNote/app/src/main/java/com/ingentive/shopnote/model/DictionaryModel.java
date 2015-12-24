package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class DictionaryModel {

    int decId;
    int sectionId;
    String itemName;

    public DictionaryModel() {

    }
    public DictionaryModel(String itemName,int sectionId) {
        this.itemName = itemName;
        this.sectionId = sectionId;
    }

    public DictionaryModel(int decId, String itemName,int sectionId) {
        this.decId = decId;
        this.itemName = itemName;
        this.sectionId = sectionId;
    }
    public int getSectionId() {
        return sectionId;
    }

    public int getDectionaryId() {
        return decId;
    }

    public String getItemName() {
        return itemName;
    }
    public void setDecId(int decId) {
        this.decId = decId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
