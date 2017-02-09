package com.boostcamp.eunjilee.innerbeauty.service;

import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by eunjilee on 06/02/2017.
 */

public interface PlayService {

    @GET("getPlay/{playId}")
    Call<PlayModel> getPlay(@Path("playId")int playId);

    @GET("getPlayList/{page}")
    Call<List<PlayModel>> getPlayList(@Path("page")int page);

    interface getPlayCallback {
        void success(PlayModel playModel);
        void error(Throwable throwable);
    }
    interface getPlayListCallback {
        void success(List<PlayModel> playModelList);
        void error(Throwable throwable);
    }

}
