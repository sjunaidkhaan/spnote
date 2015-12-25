package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class ScreenTextModel {

    int scrTxtId;
    String mName;
    String mTxt;

    public ScreenTextModel() {

    }
    public ScreenTextModel(int scrTxtId,String mName,String mTxt) {
        this.scrTxtId = scrTxtId;
        this.mName = mName;
        this.mTxt = mTxt;
    }
    public ScreenTextModel(String mName,String mTxt) {
        this.scrTxtId = scrTxtId;
        this.mName = mName;
        this.mTxt = mTxt;
    }

    public int getScrTxtId() {
        return scrTxtId;
    }

    public String getmName() {
        return mName;
    }

    public String getmTxt() {
        return mTxt;
    }
}
