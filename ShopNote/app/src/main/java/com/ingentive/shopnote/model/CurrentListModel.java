package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class CurrentListModel {
    int currListId ;
    int orderNo;
    int listNo;
    int checked;
    int favSelectedIcon;
    int favUnselectedIcon;
    String itemName;
    String listName;
    String quantity;
    public CurrentListModel() {

    }
    public CurrentListModel(int currListId,int orderNo ,String itemName,int checked, String quantity,
                            String listName, int listNo,int favSelectedIcon,int favUnselectedIcon) {
        this.currListId = currListId;
        this.orderNo = orderNo;
        this.listNo = listNo;
        this.itemName = itemName;
        this.quantity = quantity;
        this.listName = listName;
        this.checked = checked;
        this.favSelectedIcon = favSelectedIcon;
        this.favUnselectedIcon = favUnselectedIcon;
    }
    public CurrentListModel(int orderNo ,String itemName,int checked, String quantity,
                            String listName, int listNo,int favSelectedIcon,int favUnselectedIcon) {
        this.orderNo = orderNo;
        this.itemName = itemName;
        this.checked = checked;
        this.quantity = quantity;
        this.listName = listName;
        this.listNo = listNo;
        this.favSelectedIcon = favSelectedIcon;
        this.favUnselectedIcon = favUnselectedIcon;

    }
    public CurrentListModel(int orderNo ,String itemName,int checked, String quantity,
                            String listName, int listNo) {
        this.orderNo = orderNo;
        this.itemName = itemName;
        this.checked = checked;
        this.quantity = quantity;
        this.listName = listName;
        this.listNo = listNo;
    }


    public int getFavSelectedIcon() {
        return favSelectedIcon;
    }

    public void setFavSelectedIcon(int favSelectedIcon) {
        this.favSelectedIcon = favSelectedIcon;
    }

    public int getFavUnselectedIcon() {
        return favUnselectedIcon;
    }

    public void setFavUnselectedIcon(int favUnselectedIcon) {
        this.favUnselectedIcon = favUnselectedIcon;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setCurrListId(int currListId) {
        this.currListId = currListId;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public void setListNo(int listNo) {
        this.listNo = listNo;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public int getChecked() {
        return checked;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getListNo() {
        return listNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public int getCurrListId() {
        return currListId;
    }
}
