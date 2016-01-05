package com.ingentive.shopnote.model;

public class ManageSectionModel {


    int msId;
    String optionIcon;
    String sectionIcon;
    String sectionName;
    public ManageSectionModel() {

    }

    public String getOptionIcon() {
        return optionIcon;
    }

    public void setOptionIcon(String optionIcon) {
        this.optionIcon = optionIcon;
    }

    public ManageSectionModel(int msId,String optionIcon, String sectionIcon,String sectionName) {
        this.msId = msId;
        this.optionIcon = optionIcon;
        this.sectionName = sectionName;
        this.sectionIcon = sectionIcon;
    }
    public ManageSectionModel(String optionIcon, String sectionIcon,String sectionName) {
        this.optionIcon = optionIcon;
        this.sectionName = sectionName;
        this.sectionIcon = sectionIcon;
    }
    public int getMsId() {
        return msId;
    }

    public void setMsId(int msId) {
        this.msId = msId;
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