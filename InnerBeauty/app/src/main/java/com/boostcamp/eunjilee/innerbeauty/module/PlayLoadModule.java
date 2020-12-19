package com.boostcamp.eunjilee.innerbeauty.module;

import static com.boostcamp.eunjilee.innerbeauty.InnerBeautyActivity.SERVER_PREFIX;

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
    private final static String SERVER_URL = SERVER_PREFIX + "Play/";

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
                    Log.d("PlayLoadModule Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PlayModel> call, Throwable t) {
                Log.d("PlayLoadModule Retrofit", "Fail to Async Callback");
                callback.error(t);
            }
        });
    }

    public static void addClickNumToPlay(int playId, final PlayService.addClickNumCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayService playService = retrofit.create(PlayService.class);
        Call<Boolean> call = playService.addClickNumToPlay(playId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.success();
                } else {
                    Log.d("PlayLoadModule Retrofit", "Error Http Code = " + response.code());
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("PlayLoadModule Retrofit", "Fail to Asnyc Callback");
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
                    Log.d("PlayLoadModule Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PlayModel>> call, Throwable t) {
                Log.d("PlayLoadModule Retrofit", "Fail to Async Callback");
                callback.error(t);
            }
        });
    }

    /*
    * 앱 전체 사용자들에게 관심을 많이 받은 연극 컨첸츠를 불러온다
    * 기준 : 현재는 click수로 하였다 (향후 변경 가능)
    * */
    public static void getGlobalFavoritePlayList(final PlayService.getGlobalFavoritePlayListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlayService playService = retrofit.create(PlayService.class);
        Call<List<PlayModel>> call = playService.getGlobalFavoritePlay();
        call.enqueue(new Callback<List<PlayModel>>() {
            @Override
            public void onResponse(Call<List<PlayModel>> call, Response<List<PlayModel>> response) {
                if (response.isSuccessful()) {
                    List<PlayModel> favoritePlayModelList = response.body();
                    callback.success(favoritePlayModelList);
                } else {
                    Log.d("PlayLoadModule Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PlayModel>> call, Throwable t) {
                Log.d("PlayLoadModule Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }
}
