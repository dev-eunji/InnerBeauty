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
    @SerializedName("profile_image")
    public String profileImagePath;
    //TODO : pw를 클래스변수로 가지고 있는게 보안상 문제가 있을 것 같다.
    //String userPw;
}
