package com.ingentive.shopnote.model;

public class NavDrawerItemModel {
    private boolean showNotify;
    private String title;

    int iconId;
    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public NavDrawerItemModel() {

    }

    public NavDrawerItemModel(boolean showNotify, String title) {
        this.showNotify = showNotify;
        this.title = title;
    }
    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
