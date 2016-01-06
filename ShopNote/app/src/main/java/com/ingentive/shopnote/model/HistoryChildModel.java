package com.ingentive.shopnote.model;

/**
 * Created by PC on 12/23/2015.
 */
public class HistoryChildModel {

    int his_ch_id;
    String his_ch_item_name;

    public HistoryChildModel() {

    }
    public HistoryChildModel(int his_ch_id, String itemName) {
        this.his_ch_id = his_ch_id;
        this.his_ch_item_name = itemName;
    }
    public HistoryChildModel(String itemName) {
        this.his_ch_item_name = itemName;
    }

    public int getHisChId() {
        return his_ch_id;
    }

    public void setHisChId(int his_ch_id) {
        this.his_ch_id = his_ch_id;
    }

    public String getHisChItemName() {
        return his_ch_item_name;
    }

    public void setHisChItemName(String his_ch_item_name) {
        this.his_ch_item_name = his_ch_item_name;
    }

}
