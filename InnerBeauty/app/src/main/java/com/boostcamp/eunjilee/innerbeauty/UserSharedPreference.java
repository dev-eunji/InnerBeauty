package com.boostcamp.eunjilee.innerbeauty;

import android.content.Context;
import android.content.SharedPreferences;

import com.boostcamp.eunjilee.innerbeauty.model.UserModel;

/**
 * Created by eunjilee on 13/02/2017.
 */

public class UserSharedPreference {
    private static final String USER = "USER";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_EMAIL = "USER_EMAIL";
    private static final String USER_PROFILE_IMAGE = "USER_PROFILE_IMAGE";
    private static final String USER_SNS_TYPE = "USER_SNS_TYPE";

    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;

    public UserSharedPreference(Context context) {
        mSharedPreference = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
    }


    public void setUserInfo(UserModel userModel) {
        mEditor = mSharedPreference.edit();
        mEditor.putLong(USER_ID, userModel.getUserId());
        mEditor.putString(USER_NAME, userModel.getUserName());
        mEditor.putString(USER_EMAIL, userModel.getUserEmail());
        mEditor.putString(USER_PROFILE_IMAGE, userModel.getUserProfile());
        mEditor.putInt(USER_SNS_TYPE, userModel.getLoginSnsType());
        mEditor.apply();
    }

    public void resetUserInfo(){
        mEditor = mSharedPreference.edit();
        mEditor.putLong(USER_ID, 0);
        mEditor.putString(USER_NAME, "");
        mEditor.putString(USER_EMAIL, "");
        mEditor.putString(USER_PROFILE_IMAGE, "");
        mEditor.putInt(USER_SNS_TYPE, 0);
        mEditor.apply();
    }
    public long getUserId() {
        return mSharedPreference.getLong(USER_ID, 0);
    }

    public String getUserName() {
        return mSharedPreference.getString(USER_NAME, null);
    }

    public String getUserEmail() {
        return mSharedPreference.getString(USER_EMAIL, null);
    }

    public String getUserProfileImage() {
        return mSharedPreference.getString(USER_PROFILE_IMAGE, null);
    }

    public int getUserSnsType() {
        return mSharedPreference.getInt("userSnsType", 0);
    }

}
