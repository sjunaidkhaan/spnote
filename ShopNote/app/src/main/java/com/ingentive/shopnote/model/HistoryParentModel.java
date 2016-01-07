package com.ingentive.shopnote.model;

import java.util.List;

/**
 * Created by PC on 12/23/2015.
 */
public class HistoryParentModel {

    int his_par_id;
    int his_par_add_icon;
    String his_pa_dpur;

    public boolean isClick() {
        return isClick;
    }

    public void setIsClick(boolean isClick) {
        this.isClick = isClick;
    }

    boolean isClick;
    private List<HistoryChildModel> mHisChildrenList;

    public HistoryParentModel() {

    }
    public HistoryParentModel(int historyId,int addIcon) {
        this.his_par_id = historyId;
        this.his_par_add_icon = addIcon;
        this.isClick = false;
    }
    public HistoryParentModel(int addIcon) {
        this.his_par_add_icon = addIcon;
    }
    public String getHisPaDatePurchased() {
        return his_pa_dpur;
    }

    public int getHisPaId() {
        return his_par_id;
    }
    public void setHisPaId(int historyId) {
        this.his_par_id = historyId;
    }

    public void setHisPaDatePurchased(String datePurchased) {
        this.his_pa_dpur = datePurchased;
    }
    public List<HistoryChildModel> getArrayChildren() {
        return mHisChildrenList;
    }

    public void setArrayChildren(List<HistoryChildModel> arrayChildren) {
        mHisChildrenList = arrayChildren;
    }

}
