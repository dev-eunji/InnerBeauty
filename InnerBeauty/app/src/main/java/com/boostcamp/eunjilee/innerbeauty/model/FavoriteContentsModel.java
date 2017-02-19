package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eunjilee on 13/02/2017.
 */

public class FavoriteContentsModel {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("contents_id")
    private int contentsId;
    @SerializedName("contents_type")
    private int contentsType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContentsId() {
        return contentsId;
    }

    public void setContentsId(int contentsId) {
        this.contentsId = contentsId;
    }

    public int getContentsType() {
        return contentsType;
    }

    public void setContentsType(int contentsType) {
        this.contentsType = contentsType;
    }
}
