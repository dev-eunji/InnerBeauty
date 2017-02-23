package com.boostcamp.eunjilee.innerbeauty.module;

import static com.boostcamp.eunjilee.innerbeauty.InnerBeautyActivity.SERVER_PREFIX;

import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.model.MapModel;
import com.boostcamp.eunjilee.innerbeauty.service.MapService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 20/02/2017.
 */

public class MapModule {
    private final static String SERVER_URL = SERVER_PREFIX + "Map/";

    public static void getLatLngFromAddressCallback(String address, final MapService.getLatLngFromAddressCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MapService mapService = retrofit.create(MapService.class);
        Call<MapModel> call = mapService.getLatLngFromAddress(address);
        call.enqueue(new Callback<MapModel>() {
            @Override
            public void onResponse(Call<MapModel> call, Response<MapModel> response) {
                if (response.isSuccessful()) {
                    MapModel mapModel = response.body();
                    callback.success(mapModel);
                } else {
                    Log.d("map Retrofit", "Error Http Code = " + response.code());

                }
            }
            @Override
            public void onFailure(Call<MapModel> call, Throwable t) {
                Log.d("map Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

}
