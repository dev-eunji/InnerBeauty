package com.boostcamp.eunjilee.innerbeauty.service;

import com.boostcamp.eunjilee.innerbeauty.model.SearchContentsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by eunjilee on 19/02/2017.
 */

public interface SearchService {
    @GET("searchContentsList/{searchWord}")
    Call<List<SearchContentsModel>> searchContentsList(@Path("searchWord") String searchWord);

    interface searchContentsListCallback {
        void success(List<SearchContentsModel> contentsModelList);
        void error(Throwable throwable);
    }
}
