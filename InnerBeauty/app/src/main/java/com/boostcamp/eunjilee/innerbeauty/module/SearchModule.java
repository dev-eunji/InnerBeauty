package com.boostcamp.eunjilee.innerbeauty.module;

import static com.boostcamp.eunjilee.innerbeauty.InnerBeautyActivity.SERVER_PREFIX;

import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.model.SearchContentsModel;
import com.boostcamp.eunjilee.innerbeauty.service.SearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 19/02/2017.
 */

public class SearchModule {
    private final static String SERVER_URL = SERVER_PREFIX + "Search/";

    public static void searchContentsList(String searchWord, final SearchService.searchContentsListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SearchService searchService = retrofit.create(SearchService.class);
        Call<List<SearchContentsModel>> call = searchService.searchContentsList(searchWord);
        call.enqueue(new Callback<List<SearchContentsModel>>() {
            @Override
            public void onResponse(Call<List<SearchContentsModel>> call, Response<List<SearchContentsModel>> response) {
                if (response.isSuccessful()) {
                    List<SearchContentsModel> searchContentsModelList = response.body();
                    callback.success(searchContentsModelList);
                } else {
                    Log.d("SearchModule Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<SearchContentsModel>> call, Throwable t) {
                Log.d("SearchModule Retrofit", "Fail to Async Callback");
                callback.error(t);
            }
        });
    }
}
