package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eunjilee on 21/02/2017.
 */

public class MapItemModel {
    @SerializedName("address")
    String address;
    @SerializedName("addrdetail")
    MapAddressDetailModel mapAddressDetail;
    @SerializedName("isRoadAddress")
    boolean isRoadAddress;
    @SerializedName("point")
    MapPointModel mapPoint;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MapAddressDetailModel getMapAddressDetail() {
        return mapAddressDetail;
    }

    public void setMapAddressDetail(
            MapAddressDetailModel mapAddressDetail) {
        this.mapAddressDetail = mapAddressDetail;
    }

    public MapPointModel getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(MapPointModel mapPoint) {
        this.mapPoint = mapPoint;
    }

    public boolean isRoadAddress() {

        return isRoadAddress;
    }

    public void setRoadAddress(boolean roadAddress) {
        isRoadAddress = roadAddress;
    }
}
