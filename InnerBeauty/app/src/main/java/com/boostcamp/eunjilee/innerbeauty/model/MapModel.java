package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eunjilee on 20/02/2017.
 */

public class MapModel {
    @SerializedName("result")
    MapResultModel mapResult;

    public MapResultModel getMapResult() {
        return mapResult;
    }

    public void setMapResult(MapResultModel mapResult) {
        this.mapResult = mapResult;
    }
}
