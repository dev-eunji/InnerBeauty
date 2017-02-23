package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eunjilee on 21/02/2017.
 */

public class MapPointModel {
    @SerializedName("x")
    double x;
    @SerializedName("y")
    double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
