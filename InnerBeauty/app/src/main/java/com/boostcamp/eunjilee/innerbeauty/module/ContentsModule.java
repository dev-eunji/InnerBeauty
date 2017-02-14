package com.boostcamp.eunjilee.innerbeauty.module;

import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.model.FavoriteContentsModel;
import com.boostcamp.eunjilee.innerbeauty.service.ContentsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 13/02/2017.
 */

public class ContentsModule {
    private final static String SERVER_URL = "http://35.166.198.97/index.php/Contents/";

    public static void getFavoriteContentsList(long userId, int contentsType, final ContentsService.getFavoriteContentsListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentsService contentsService = retrofit.create(ContentsService.class);
        Call<FavoriteContentsModel> call = contentsService.getFavoriteContentsList(userId, contentsType);
        call.enqueue(new Callback<FavoriteContentsModel>() {
            @Override
            public void onResponse(Call<FavoriteContentsModel> call, Response<FavoriteContentsModel> response) {
                if (response.isSuccessful()) {
                    FavoriteContentsModel favoriteContentsModel = response.body();
                    callback.success(favoriteContentsModel);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FavoriteContentsModel> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    public void registerFavoriteContents(long userId, int contentId, int contentsType, final ContentsService.registerFavoriteContentsCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentsService contentsService = retrofit.create(ContentsService.class);
        Call<FavoriteContentsModel> call = contentsService.registerFavoriteContents(userId, contentId, contentsType);
        call.enqueue(new Callback<FavoriteContentsModel>() {
            @Override
            public void onResponse(Call<FavoriteContentsModel> call, Response<FavoriteContentsModel> response) {
                if (response.isSuccessful()) {
                    callback.success();
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FavoriteContentsModel> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    public void deleteFavoriteContents(long userId, int contentId, int contentsType, final ContentsService.deleteFavoriteContentsCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentsService contentsService = retrofit.create(ContentsService.class);
        Call<FavoriteContentsModel> call = contentsService.deleteFavoriteContents(userId, contentId, contentsType);
        call.enqueue(new Callback<FavoriteContentsModel>() {
            @Override
            public void onResponse(Call<FavoriteContentsModel> call, Response<FavoriteContentsModel> response) {
                if (response.isSuccessful()) {
                    callback.success();
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FavoriteContentsModel> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });

    }
}
