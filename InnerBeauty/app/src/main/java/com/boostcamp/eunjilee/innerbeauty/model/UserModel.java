package com.boostcamp.eunjilee.innerbeauty.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class UserModel {
    @SerializedName("user_id")
    int userId;
    @SerializedName("user_name")
    String userName;
    @SerializedName("user_email")
    public String userEmail;
    @SerializedName("sns_type")
    public String loginSnsType;
    @SerializedName("profile_image")
    public String profileImagePath;

    public int getUserId() {
        return userId;
    }

    public String getLoginSnsType() {
        return loginSnsType;
    }

    public void setLoginSnsType(String loginSnsType) {
        this.loginSnsType = loginSnsType;
    }

    public void setUserId(int userId) {
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

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }
}
