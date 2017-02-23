package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eunjilee on 21/02/2017.
 */

public class MapAddressDetailModel {
    @SerializedName("country")
    String country;
    @SerializedName("sido")
    String sido;
    @SerializedName("sigugun")
    String sigugun;
    @SerializedName("dongmyun")
    String dongmyun;
    @SerializedName("rest")
    String rest;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    public String getSigugun() {
        return sigugun;
    }

    public void setSigugun(String sigugun) {
        this.sigugun = sigugun;
    }

    public String getDongmyun() {
        return dongmyun;
    }

    public void setDongmyun(String dongmyun) {
        this.dongmyun = dongmyun;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }
}
