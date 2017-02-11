package com.boostcamp.eunjilee.innerbeauty.module;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.model.UserModel;
import com.boostcamp.eunjilee.innerbeauty.service.LoginService;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 10/02/2017.
 */

public class LoginModule {
    public static final int FACEBOOK_LOGIN = 1;
    public static final int NAVER_LOGIN = 2;

    public final static String SERVER_URL = "http://35.166.198.97/index.php/Users/";

    private LoginService.LoginCallback loginCallback;

    public void loginWithFacebook(LoginResult loginResult, final LoginService.LoginCallback loginCallback) {
        this.loginCallback = loginCallback;
        GraphRequest graphRequest = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        String userId = "", userName = "", userEmail = "";
                        try {
                            if (object.has("id")) {
                                userId = object.getString("id");
                            }
                            if (object.has("name")) {
                                userName = object.getString("name");
                            }
                            if (object.has("email")) {
                                userEmail = object.getString("email");
                            }
                            //TODO; user로 등록하기
                        } catch (JSONException e) {
                            Log.v("daisy","LoginModule");
                        }
                    }

                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    private void registerUserWithSNS(String id, String name, String email, int snsType, final LoginService.LoginCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<UserModel> call = loginService.addUserWithSNS(id, name, email, snsType);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel userModel = response.body();
                    // TODO : SharedPreference저장
                    callback.success(userModel);

                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }
}
