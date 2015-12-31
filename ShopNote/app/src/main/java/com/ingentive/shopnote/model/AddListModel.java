package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class AddListModel {

    int addListId;
    int favIcon;
    int histrIcon;
    String itemName;
    public AddListModel(){

    }

    public AddListModel(int addListId,int favIcon,int histrIcon,String itemName){
        this.addListId = addListId;
        this.favIcon = favIcon;
        this.histrIcon = histrIcon;
        this.itemName = itemName;

    }
    public AddListModel(int favIcon,int histrIcon,String itemName){
        this.favIcon = favIcon;
        this.histrIcon = histrIcon;
        this.itemName = itemName;

    }

    public int getFavIcon() {
        return favIcon;
    }

    public void setFavIcon(int favIcon) {
        this.favIcon = favIcon;
    }

    public int getAddListId() {
        return addListId;
    }

    public void setAddListId(int addListId) {
        this.addListId = addListId;
    }

    public int getHistrIcon() {
        return histrIcon;
    }

    public void setHistrIcon(int histrIcon) {
        this.histrIcon = histrIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
