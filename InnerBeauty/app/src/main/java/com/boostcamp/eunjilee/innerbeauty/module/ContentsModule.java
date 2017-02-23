package com.boostcamp.eunjilee.innerbeauty.module;

import static com.boostcamp.eunjilee.innerbeauty.InnerBeautyActivity.SERVER_PREFIX;

import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.model.FavoriteContentsModel;
import com.boostcamp.eunjilee.innerbeauty.service.ContentsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 13/02/2017.
 */

public class ContentsModule {
    private final static String SERVER_URL = SERVER_PREFIX + "Contents/";

    public static void getFavoriteContentsList(long userId, final ContentsService.getFavoriteContentsListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentsService contentsService = retrofit.create(ContentsService.class);
        Call<List<FavoriteContentsModel>> call = contentsService.getFavoriteContentsList(userId);
        call.enqueue(new Callback<List<FavoriteContentsModel>>() {
            @Override
            public void onResponse(Call<List<FavoriteContentsModel>> call, Response<List<FavoriteContentsModel>> response) {
                if (response.isSuccessful()) {
                    List<FavoriteContentsModel> favoriteContentsModel = response.body();
                    callback.success(favoriteContentsModel);
                } else {
                    Log.d("FCList Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<FavoriteContentsModel>> call, Throwable t) {
                Log.d("FCList Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    /*
    * user가 좋아하는 컨텐츠의 리스트를 contentsType에 맞춰 불러온다
    * */
    public static void getFavoriteContentsListByContentsType(long userId, int contentsType, final ContentsService.getFavoriteContentsListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ContentsService contentsService = retrofit.create(ContentsService.class);
        Call<List<FavoriteContentsModel>> call = contentsService.getFavoriteContentsListByContentsType(userId, contentsType);
        call.enqueue(new Callback<List<FavoriteContentsModel>>() {
            @Override
            public void onResponse(Call<List<FavoriteContentsModel>> call, Response<List<FavoriteContentsModel>> response) {
                if (response.isSuccessful()) {
                    List<FavoriteContentsModel> favoriteContentsModel = response.body();
                    callback.success(favoriteContentsModel);
                } else {
                    Log.d("FCListB Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<FavoriteContentsModel>> call, Throwable t) {
                Log.d("FCListB Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    public static void registerFavoriteContents(long userId, int contentId, int contentsType, final ContentsService.registerFavoriteContentsCallback callback) {
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
                    Log.d("RFC Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FavoriteContentsModel> call, Throwable t) {
                Log.d("RFC Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    public static void deleteFavoriteContents(long userId, int contentId, int contentsType, final ContentsService.deleteFavoriteContentsCallback callback) {
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
                    Log.d("DFC Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<FavoriteContentsModel> call, Throwable t) {
                Log.d("DFC Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }
}
