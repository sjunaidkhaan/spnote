package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class SettingModel {
    int settingId;
    String regEmail;
    int shopBySection;
    int favBySection;
    int listIntro;
    int shopIntro;
    int searchIntro;
    int sectionIntro;
    int favIntro;
    int historyIntro;
    int listEditIntro;
    int addIntro;
    int userRated;

    public SettingModel(int settingId,String regEmail ,int favBySection,int shopBySection,int listIntro,
                        int shopIntro,int favIntro,int historyIntro,int listEditIntro,
                        int sectionIntro,int searchIntro,int addIntro, int userRated) {
        this.settingId = settingId;
        this.regEmail = regEmail;
        this.favBySection = favBySection;
        this.shopBySection = shopBySection;
        this.listIntro = listIntro;
        this.shopIntro = shopIntro;
        this.favIntro = favIntro;
        this.historyIntro = historyIntro;
        this.listEditIntro = listEditIntro;
        this.sectionIntro = sectionIntro;
        this.searchIntro = searchIntro;
        this.addIntro = addIntro;
        this.userRated = userRated;

    }
    public SettingModel(String regEmail ,int favBySection,int shopBySection,int listIntro,
                        int shopIntro,int favIntro,int historyIntro,int listEditIntro,
                        int sectionIntro,int searchIntro,int addIntro, int userRated) {

        this.regEmail = regEmail;
        this.favBySection = favBySection;
        this.shopBySection = shopBySection;
        this.listIntro = listIntro;
        this.shopIntro = shopIntro;
        this.favIntro = favIntro;
        this.historyIntro = historyIntro;
        this.listEditIntro = listEditIntro;
        this.sectionIntro = sectionIntro;
        this.searchIntro = searchIntro;
        this.addIntro = addIntro;
        this.userRated = userRated;

    }
    public int getListIntro() {
        return listIntro;
    }
    public int getFavBySection() {
        return favBySection;
    }
    public int getShopIntro() {
        return shopIntro;
    }
    public String getRegEmail() {
        return regEmail;
    }
    public int getShopBySection() {
        return shopBySection;
    }
    public int getSearchIntro() {
        return searchIntro;
    }
    public int getSectionIntro() {
        return sectionIntro;
    }
    public int getFavIntro() {
        return favIntro;
    }
    public int getHistoryIntro() {
        return historyIntro;
    }
    public int getListEditIntro() {
        return listEditIntro;
    }
    public int getAddIntro() {
        return addIntro;
    }
    public int getUserRated() {
        return userRated;
    }

}
