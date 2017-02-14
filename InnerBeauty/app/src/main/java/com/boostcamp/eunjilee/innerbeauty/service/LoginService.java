package com.boostcamp.eunjilee.innerbeauty.service;

import com.boostcamp.eunjilee.innerbeauty.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by eunjilee on 10/02/2017.
 */

public interface LoginService {

    @GET("getLogin")
    Call<UserModel> longin(@Query("user_id") long userId, @Query("user_pw") String userPw);

    @FormUrlEncoded
    @POST("addUserWithSNS")
    Call<UserModel> addUserWithSNS(@Field("user_id") long userId, @Field("user_name") String userName, @Field("user_email") String userEmail, @Field("user_profile") String userProfilePicture, @Field("sns_type") int snsType);

    interface LoginCallback {
        void success();
        void error(Throwable throwable);
    }
}
