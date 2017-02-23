package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eunjilee on 21/02/2017.
 */

public class MapResultModel {
    @SerializedName("total")
    int totalNum;
    @SerializedName("items")
    List<MapItemModel> mapItem;

    public List<MapItemModel> getMapItem() {
        return mapItem;
    }

    public void setMapItem(List<MapItemModel> mapItem) {
        this.mapItem = mapItem;
    }


    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }


}
