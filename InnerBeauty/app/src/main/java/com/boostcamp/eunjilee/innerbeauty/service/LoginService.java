package com.boostcamp.eunjilee.innerbeauty.service;

import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by eunjilee on 10/02/2017.
 */

public interface LoginService {

    @GET("/getLogin")
    Call<UserModel> longin(@Query("user_id")String userId, @Query("user_pw")String userPw);

    @FormUrlEncoded
    @POST("/addUserWithSNS")
    Call<UserModel> addUserWithSNS(@Field("user_id") String userId, @Field("user_name") String userName, @Field("user_email") String userEmail, @Field("sns_type") int snsType);

    interface LoginCallback {
        void success(UserModel userModel);
        void error(Throwable throwable);
    }
}
