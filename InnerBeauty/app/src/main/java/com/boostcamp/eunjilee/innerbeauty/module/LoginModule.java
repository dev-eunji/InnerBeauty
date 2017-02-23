package com.boostcamp.eunjilee.innerbeauty.module;

import static com.boostcamp.eunjilee.innerbeauty.Constant.FACEBOOK_TYPE;
import static com.boostcamp.eunjilee.innerbeauty.Constant.KAKAO_TYPE;
import static com.boostcamp.eunjilee.innerbeauty.Constant.NAVER_TYPE;
import static com.boostcamp.eunjilee.innerbeauty.InnerBeautyActivity.SERVER_PREFIX;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.boostcamp.eunjilee.innerbeauty.UserSharedPreference;
import com.boostcamp.eunjilee.innerbeauty.model.PlayModel;

import com.boostcamp.eunjilee.innerbeauty.model.UserModel;
import com.boostcamp.eunjilee.innerbeauty.service.LoginService;
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
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    private final static String SERVER_URL = SERVER_PREFIX + "Users/";

    private static UserSharedPreference mUserSharedPreference;

    public LoginModule(Context context) {
        mUserSharedPreference = new UserSharedPreference(context);
    }

    public static void loginWithNaver(String data, final LoginService.LoginCallback callback){
        String userEmail = "", userProfilePicture = "", userId = "", userName = "", birthday = "";
        String f_array[] = new String[9];

        try {
            XmlPullParserFactory parserCreator = XmlPullParserFactory
                    .newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            InputStream input = new ByteArrayInputStream(
                    data.getBytes("UTF-8"));
            parser.setInput(input, "UTF-8");

            int parserEvent = parser.getEventType();
            String tag;
            boolean inText = false;
            boolean lastMatTag = false;

            int colIdx = 0;

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.compareTo("xml") == 0) {
                            inText = false;
                        } else if (tag.compareTo("data") == 0) {
                            inText = false;
                        } else if (tag.compareTo("result") == 0) {
                            inText = false;
                        } else if (tag.compareTo("resultcode") == 0) {
                            inText = false;
                        } else if (tag.compareTo("message") == 0) {
                            inText = false;
                        } else if (tag.compareTo("response") == 0) {
                            inText = false;
                        } else {
                            inText = true;

                        }
                        break;
                    case XmlPullParser.TEXT:
                        tag = parser.getName();
                        if (inText) {
                            if (parser.getText() == null) {
                                f_array[colIdx] = "";
                            } else {
                                f_array[colIdx] = parser.getText().trim();
                            }

                            colIdx++;
                        }
                        inText = false;
                        break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        inText = false;
                        break;

                }

                parserEvent = parser.next();
            }
        } catch (Exception e) {
            Log.e("dd", "Error in network call", e);
        }
        userEmail = f_array[0];
        userProfilePicture = f_array[3];
        userId = f_array[6];
        userName = f_array[7];
        registerUserWithSNS(Long.valueOf(userId), userName, userEmail, userProfilePicture, NAVER_TYPE, callback);

    }

    public static void loginWithFacebook(LoginResult loginResult, final LoginService.LoginCallback loginCallback) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String userId = "", userName = "", userEmail = "", userProfilePicture = "";
                            if (object.has("id")) {
                                userId = object.getString("id");
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
                            registerUserWithSNS(Long.valueOf(userId), userName, userEmail, userProfilePicture, FACEBOOK_TYPE, loginCallback);
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

    public static void loginWithKakao(final LoginService.LoginCallback loginCallback) {
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
                        String.valueOf(userProfile.getUUID()), String.valueOf(userProfile.getThumbnailImagePath()), KAKAO_TYPE, loginCallback);
            }

            @Override
            public void onNotSignedUp() {
                loginCallback.error(new KakaoException("카카오 아이디가 없습니다"));
            }
        });
    }

    private static void registerUserWithSNS(long id, String name, String email, String profilePicture, int snsType, final LoginService.LoginCallback callback) {
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
                    Log.d("LoginModule Retrofit", "Error Http Code = " + response.code()); //TODO:이미 저장되어있으면 500에러 발생 해결 > id가 있는지 검사할 필요가 있을 듯

                }
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.d("LoginModule Retrofit", "Fail to Asnyc Callback");
                callback.error(t);
            }
        });
    }
}

