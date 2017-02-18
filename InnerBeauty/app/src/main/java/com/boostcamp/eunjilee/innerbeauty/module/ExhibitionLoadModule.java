package com.boostcamp.eunjilee.innerbeauty.module;

import static com.boostcamp.eunjilee.innerbeauty.InnerBeautyActivity.SERVER_PREFIX;

import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.model.ExhibitionModel;
import com.boostcamp.eunjilee.innerbeauty.service.ExhibitionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 06/02/2017.
 */

public class ExhibitionLoadModule {
    private final static String SERVER_URL = SERVER_PREFIX + "Exhibition/";

    public static void getExhibitionByAsync(int exhibitionId, final ExhibitionService.getExhibitionCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExhibitionService exhibitionService = retrofit.create(ExhibitionService.class);
        Call<ExhibitionModel> call = exhibitionService.getExhibition(exhibitionId);
        call.enqueue(new Callback<ExhibitionModel>() {
            @Override
            public void onResponse(Call<ExhibitionModel> call, Response<ExhibitionModel> response) {
                if (response.isSuccessful()) {
                    ExhibitionModel exhibitionModel = response.body();
                    callback.success(exhibitionModel);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }
            @Override
            public void onFailure(Call<ExhibitionModel> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    public static void getExhibitionListByAsync(int page, final ExhibitionService.getExhibitionListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExhibitionService exhibitionService = retrofit.create(ExhibitionService.class);
        Call<List<ExhibitionModel>> call = exhibitionService.getExhibitionList(page);
        call.enqueue(new Callback<List<ExhibitionModel>>() {
            @Override
            public void onResponse(Call<List<ExhibitionModel>> call, Response<List<ExhibitionModel>> response) {
                if (response.isSuccessful()) {
                    List<ExhibitionModel> exhibitionModelList = response.body();
                    callback.success(exhibitionModelList);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ExhibitionModel>> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    public static void addClickNumToExhibition(int exhibitionId, final ExhibitionService.addClickNumCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExhibitionService exhibitionService = retrofit.create(ExhibitionService.class);
        Call<Boolean> call = exhibitionService.addClickNumToExhibition(exhibitionId);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.success();
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

    /*
    * 앱 전체 사용자들에게 관심을 많이 받은 전시 컨첸츠를 불러온다
    * 기준 : 현재는 click수로 하였다 (향후 변경 가능)
    * */
    public static void getGlobalFavoriteExhibitionList(final ExhibitionService.getGlobalFavoriteExhibitionListCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ExhibitionService exhibitionService = retrofit.create(ExhibitionService.class);
        Call<List<ExhibitionModel>> call = exhibitionService.getGlobalFavoriteExhibition();
        call.enqueue(new Callback<List<ExhibitionModel>>() {
            @Override
            public void onResponse(Call<List<ExhibitionModel>> call, Response<List<ExhibitionModel>> response) {
                if (response.isSuccessful()) {
                    List<ExhibitionModel> favoriteEhibitionModelList = response.body();
                    callback.success(favoriteEhibitionModelList);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<ExhibitionModel>> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

}
