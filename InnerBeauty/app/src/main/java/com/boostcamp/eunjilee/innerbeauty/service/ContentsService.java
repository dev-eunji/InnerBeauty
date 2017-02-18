package com.boostcamp.eunjilee.innerbeauty.service;
import com.boostcamp.eunjilee.innerbeauty.model.FavoriteContentsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by eunjilee on 13/02/2017.
 */

public interface ContentsService {
    @GET("getFavoriteContentsList/{userId}/{contentsType}")
    Call<FavoriteContentsModel> getFavoriteContentsList(@Path("userId") long userId, @Path("contentsType") int contentsType);

    @FormUrlEncoded
    @POST("registerFavoriteContents")
    Call<FavoriteContentsModel> registerFavoriteContents(@Field("user_id") long userId, @Field("contents_id") int contentsId,@Field("contents_type") int contentsType );

    @FormUrlEncoded
    @POST("deleteFavoriteContents")
    Call<FavoriteContentsModel> deleteFavoriteContents(@Field("user_id") long userId, @Field("contents_id") int contentsId,@Field("contents_type") int contentsType );

    interface getFavoriteContentsListCallback {
        void success(FavoriteContentsModel favoriteContentsModel);
        void error(Throwable throwable);
    }

    interface registerFavoriteContentsCallback {
        void success();
        void error(Throwable throwable);
    }
    interface deleteFavoriteContentsCallback{
        void success();
        void error(Throwable throwable);
    }
}
