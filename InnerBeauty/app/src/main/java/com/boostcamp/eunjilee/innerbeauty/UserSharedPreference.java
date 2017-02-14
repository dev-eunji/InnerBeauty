package com.boostcamp.eunjilee.innerbeauty;

import android.content.Context;
import android.content.SharedPreferences;

import com.boostcamp.eunjilee.innerbeauty.model.UserModel;

/**
 * Created by eunjilee on 13/02/2017.
 */

public class UserSharedPreference {
    private SharedPreferences mSharedPreference;
    private SharedPreferences.Editor mEditor;

    public UserSharedPreference(Context context) {
        mSharedPreference = context.getSharedPreferences("USER", Context.MODE_PRIVATE);
        mEditor = mSharedPreference.edit();
    }


    public void setUserInfo(UserModel userModel) {
        mEditor.putLong("userId", userModel.getUserId());
        mEditor.putString("userName", userModel.getUserName());
        mEditor.putString("userEmail", userModel.getUserEmail());
        mEditor.putString("userProfileImage", userModel.getUserProfile());
        mEditor.putInt("userSnsType", userModel.getLoginSnsType());
        mEditor.apply();
    }

    public long getUserId(){
        return mSharedPreference.getLong("userId",0);
    }
    public String getUserName(){
        return mSharedPreference.getString("userName", null);
    }
    public String getUserEmail(){
        return mSharedPreference.getString("userEmail", null);
    }
    public String getUserProfileImage(){
        return mSharedPreference.getString("userProfileImage", null);
    }
    public int getUserSnsType(){
        return mSharedPreference.getInt("userSnsType", 0);
    }

}
