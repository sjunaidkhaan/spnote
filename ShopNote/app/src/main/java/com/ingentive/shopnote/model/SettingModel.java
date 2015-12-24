package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class SettingModel {
    int settingId;
    String regEmail;
    boolean listNo;
    boolean shopBySection;
    boolean listIntro;
    boolean searchIntro;

    public boolean isSectionIntro() {
        return sectionIntro;
    }

    public void setSectionIntro(boolean sectionIntro) {
        this.sectionIntro = sectionIntro;
    }

    boolean sectionIntro;
    boolean favIntro;
    boolean historyIntro;
    boolean listEditIntro;
    boolean addIntro;
    boolean userRated;

    public SettingModel() {

    }
    public SettingModel(int settingId,String regEmail ,boolean listNo,boolean shopBySection,
                        boolean listIntro,boolean searchIntro,boolean sectionIntro,boolean favIntro,boolean historyIntro,
                        boolean listEditIntro, boolean addIntro,boolean userRated) {
        this.settingId = settingId;
        this.sectionIntro = sectionIntro;
        this.regEmail = regEmail;
        this.listNo = listNo;
        this.shopBySection = shopBySection;
        this.listIntro = listIntro;
        this.searchIntro = searchIntro;
        this.favIntro = favIntro;
        this.historyIntro = historyIntro;
        this.listEditIntro = listEditIntro;
        this.addIntro = addIntro;
        this.userRated = userRated;

    }
    public SettingModel(String regEmail ,boolean listNo,boolean shopBySection,
                        boolean listIntro,boolean searchIntro,boolean sectionIntro,boolean favIntro,boolean historyIntro,
                        boolean listEditIntro, boolean addIntro,boolean userRated) {
        this.sectionIntro = sectionIntro;
        this.regEmail = regEmail;
        this.listNo = listNo;
        this.shopBySection = shopBySection;
        this.listIntro = listIntro;
        this.searchIntro = searchIntro;
        this.favIntro = favIntro;
        this.historyIntro = historyIntro;
        this.listEditIntro = listEditIntro;
        this.addIntro = addIntro;
        this.userRated = userRated;

    }

    public boolean isListNo() {
        return listNo;
    }

    public void setListNo(boolean listNo) {
        this.listNo = listNo;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public boolean isShopBySection() {
        return shopBySection;
    }

    public void setShopBySection(boolean shopBySection) {
        this.shopBySection = shopBySection;
    }

    public boolean isListIntro() {
        return listIntro;
    }

    public void setListIntro(boolean listIntro) {
        this.listIntro = listIntro;
    }

    public boolean isSearchIntro() {
        return searchIntro;
    }

    public void setSearchIntro(boolean searchIntro) {
        this.searchIntro = searchIntro;
    }

    public boolean isFavIntro() {
        return favIntro;
    }

    public void setFavIntro(boolean favIntro) {
        this.favIntro = favIntro;
    }

    public boolean isHistoryIntro() {
        return historyIntro;
    }

    public void setHistoryIntro(boolean historyIntro) {
        this.historyIntro = historyIntro;
    }

    public boolean isListEditIntro() {
        return listEditIntro;
    }

    public void setListEditIntro(boolean listEditIntro) {
        this.listEditIntro = listEditIntro;
    }

    public boolean isAddIntro() {
        return addIntro;
    }

    public void setAddIntro(boolean addIntro) {
        this.addIntro = addIntro;
    }

    public boolean isUserRated() {
        return userRated;
    }

    public void setUserRated(boolean userRated) {
        this.userRated = userRated;
    }


}
