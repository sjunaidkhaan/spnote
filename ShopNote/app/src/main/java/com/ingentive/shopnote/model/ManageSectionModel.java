package com.ingentive.shopnote.model;



public class ManageSectionModel {

    int manageSectionId;
    String optionIcon;
    String sectionIcon;
    String sectionName;
    int orderNo;

    public int getManageSectionId() {
        return manageSectionId;
    }

    public void setManageSectionId(int manageSectionId) {
        this.manageSectionId = manageSectionId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public ManageSectionModel() {

    }

    public String getOptionIcon() {
        return optionIcon;
    }

    public void setOptionIcon(String optionIcon) {
        this.optionIcon = optionIcon;
    }

    public ManageSectionModel(int msId,String optionIcon, String sectionIcon,String sectionName,int orderNo) {
        this.manageSectionId = msId;
        this.orderNo = orderNo;
        this.optionIcon = optionIcon;
        this.sectionName = sectionName;
        this.sectionIcon = sectionIcon;
    }
    public ManageSectionModel(String optionIcon, String sectionIcon,String sectionName,int orderNo) {
        this.orderNo = orderNo;
        this.optionIcon = optionIcon;
        this.sectionName = sectionName;
        this.sectionIcon = sectionIcon;
    }

    public String getSectionIcon() {
        return sectionIcon;
    }

    public void setSectionIcon(String sectionIcon) {
        this.sectionIcon = sectionIcon;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String itemName) {
        this.sectionName = itemName;
    }

}