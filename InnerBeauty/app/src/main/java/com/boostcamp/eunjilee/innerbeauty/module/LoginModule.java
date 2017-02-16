package com.boostcamp.eunjilee.innerbeauty.module;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.UserSharedPreference;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;
import com.boostcamp.eunjilee.innerbeauty.model.UserModel;
import com.boostcamp.eunjilee.innerbeauty.service.LoginService;
import com.boostcamp.eunjilee.innerbeauty.service.PlayService;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eunjilee on 10/02/2017.
 */

public class LoginModule {
    private final static String SERVER_URL = "http://35.166.198.97/index.php/Users/";

    private static final int FACEBOOK_LOGIN = 1;
    private static final int NAVER_LOGIN = 2;
    private static final int KAKAO_LOGIN = 3;

    private final UserSharedPreference mUserSharedPreference;

    public LoginModule(Context context) {
        mUserSharedPreference = new UserSharedPreference(context);
    }

    public void loginWithFacebook(LoginResult loginResult, final LoginService.LoginCallback loginCallback) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            long userId = 0;
                            String userName = "", userEmail = "", userProfilePicture = "";
                            if (object.has("id")) {
                                userId = object.getLong("id");
                            }
                            if (object.has("name")) {
                                userName = object.getString("name");
                            }
                            if (object.has("email")) {
                                userEmail = object.getString("email");
                            }
                            if (object.has("picture") && object.has("id")) {
                                userProfilePicture = "https://graph.facebook.com/v2.5/" + userId + "/picture?type=large";
                            }
                            registerUserWithSNS(userId, userName, userEmail, userProfilePicture, FACEBOOK_LOGIN, loginCallback);
                        } catch (JSONException e) {
                            Log.v("daisy", "LoginModule");
                        }
                    }

                }
        );
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    public void loginWithKakao(final LoginService.LoginCallback loginCallback) {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                loginCallback.error(new KakaoException(errorResult.getErrorMessage()));
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                loginCallback.error(new KakaoException(errorResult.getErrorMessage()));
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                registerUserWithSNS(userProfile.getId(), String.valueOf(userProfile.getNickname()),
                        String.valueOf(userProfile.getUUID()), String.valueOf(userProfile.getThumbnailImagePath()), KAKAO_LOGIN, loginCallback);
            }

            @Override
            public void onNotSignedUp() {
                loginCallback.error(new KakaoException("카카오 아이디가 없습니다"));
            }
        });
    }

    private void registerUserWithSNS(long id, String name, String email, String profilePicture, int snsType, final LoginService.LoginCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginService loginService = retrofit.create(LoginService.class);
        Call<UserModel> call = loginService.addUserWithSNS(id, name, email, profilePicture, snsType); // 여기까지 값은 정상
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    UserModel userModel = response.body();
                    mUserSharedPreference.setUserInfo(userModel);
                    callback.success();
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code()); //TODO:이미 저장되어있으면 500에러 발생 해결 > id가 있는지 검사할 필요가 있을 듯

                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }
    /*
    * 앱 전체 사용자들에게 관심을 많이 받은 연극 컨첸츠를 불러온다
    * 기준 : 현재는 click수로 하였다 (향후 변경 가능)
    * */
    public static void getGlobalFavoriteExhibitionList(final PlayService.getGlobalFavoritePlayListCallback callback) {
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
                    List<PlayModel> favoriteEhibitionModelList = response.body();
                    callback.success(favoriteEhibitionModelList);
                } else {
                    Log.d("Retrofit", "Error Http Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<PlayModel>> call, Throwable t) {
                Log.d("Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }

}

