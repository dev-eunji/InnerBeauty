package com.boostcamp.eunjilee.innerbeauty.module;

import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class PlayLoadModule {
    private final static String SERVER_URL = "http://35.166.198.97/index.php/Contents/";

    public static void getPlayByAsync(int playId, final PlayService.getPlayCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayService playService = retrofit.create(PlayService.class);
        Call<PlayModel> call = playService.getPlay(playId);
        call.enqueue(new Callback<PlayModel>() {
            @Override
            public void onResponse(Call<PlayModel> call, Response<PlayModel> response) {
                if (response.isSuccessful()) {
                    PlayModel playModel = response.body();
                    callback.success(playModel);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PlayModel> call, Throwable t) {
                Log.d("Retrofit", "Fail to Async Callback");
                callback.error(t);
            }
        });
    }

    public static void getPlayListByAsync(int page, final PlayService.getPlayListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayService playService = retrofit.create(PlayService.class);
        Call<List<PlayModel>> call = playService.getPlayList(page);
        call.enqueue(new Callback<List<PlayModel>>() {
            @Override
            public void onResponse(Call<List<PlayModel>> call, Response<List<PlayModel>> response) {
                if (response.isSuccessful()) {
                    List<PlayModel> playModelList = response.body();
                    callback.success(playModelList);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PlayModel>> call, Throwable t) {
                Log.d("Retrofit", "Fail to Async Callback");
                callback.error(t);
            }
        });
    }
}
