package com.boostcamp.eunjilee.innerbeauty.service;

import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by eunjilee on 06/02/2017.
 */

public interface PlayService {

    @GET("getPlay/{playId}")
    Call<PlayModel> getPlay(@Path("playId") int playId);

    @GET("getPlayList/{page}")
    Call<List<PlayModel>> getPlayList(@Path("page") int page);

    @FormUrlEncoded
    @POST("addClickNumToPlay")
    Call<Boolean> addClickNumToPlay(@Field("play_id") int playId);

    @GET("getGlobalFavoritePlay")
    Call<List<PlayModel>> getGlobalFavoritePlay();

    interface getPlayCallback {
        void success(PlayModel playModel);
        void error(Throwable throwable);
    }
    interface getPlayListCallback {
        void success(List<PlayModel> playModelList);
        void error(Throwable throwable);
    }
    interface addClickNumCallback {
        void success();
        void error(Throwable throwable);
    }
    interface getGlobalFavoritePlayListCallback {
        void success(List<PlayModel> playModelList);
        void error(Throwable throwable);
    }
}
