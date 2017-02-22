package com.boostcamp.eunjilee.innerbeauty.service;

import android.widget.Toast;

import com.boostcamp.eunjilee.innerbeauty.model.MapModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by eunjilee on 20/02/2017.
 */
public interface MapService {
    @FormUrlEncoded
    @POST("getLatLngFromAddress")
    Call<MapModel> getLatLngFromAddress(@Field("address") String address);

    interface getLatLngFromAddressCallback {
        void success(MapModel mapModelList);
        void error(Throwable throwable);
    }
}