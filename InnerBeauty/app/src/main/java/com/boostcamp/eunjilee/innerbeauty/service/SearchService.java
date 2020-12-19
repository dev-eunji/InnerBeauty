package com.boostcamp.eunjilee.innerbeauty.service;

import com.boostcamp.eunjilee.innerbeauty.model.SearchContentsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by eunjilee on 19/02/2017.
 */

public interface SearchService {
    @FormUrlEncoded
    @POST("searchContentsList")
    Call<List<SearchContentsModel>> searchContentsList(@Field("search_word") String searchWord);

    interface searchContentsListCallback {
        void success(List<SearchContentsModel> contentsModelList);
        void error(Throwable throwable);
    }
}
