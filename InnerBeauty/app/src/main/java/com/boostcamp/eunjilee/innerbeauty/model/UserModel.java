package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class UserModel {
    @SerializedName("user_id")
    private long userId;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("user_email")
    private String userEmail;
    @SerializedName("user_profile")
    private String userProfile;
    @SerializedName("sns_type")
    private int loginSnsType;


    public long getUserId() {
        return userId;
    }

    public int getLoginSnsType() {
        return loginSnsType;
    }

    public void setLoginSnsType(int loginSnsType) {
        this.loginSnsType = loginSnsType;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }
}
