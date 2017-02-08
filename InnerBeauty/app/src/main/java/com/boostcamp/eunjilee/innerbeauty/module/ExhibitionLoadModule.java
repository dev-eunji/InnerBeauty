package com.boostcamp.eunjilee.innerbeauty.module;

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
    public final static String SERVER_URL = "http://35.166.198.97/index.php/Contents/";

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

}
