package com.boostcamp.eunjilee.innerbeauty;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.boostcamp.eunjilee.innerbeauty.module.LoginModule;
import com.boostcamp.eunjilee.innerbeauty.service.LoginService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kakao.auth.Session.getCurrentSession;
import static com.nhn.android.naverlogin.OAuthLogin.mOAuthLoginHandler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager mFacebookCallbackManager;
    private SessionCallback mKakaoCallback;
    private LoginModule mLoginModule;
    private static OAuthLogin mOAuthLoginModule;

    //TODO: properties로 옮기
    private static String OAUTH_CLIENT_ID = "IIUrL9SHY62I8qM3OdtT";
    private static String OAUTH_CLIENT_SECRET = "0FN4pPMj4Z";
    private static String OAUTH_CLIENT_NAME = "InnerBeauty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InnerBeautyActivity.setCurrentActivity(this);
        ButterKnife.bind(this);
        mLoginModule = new LoginModule(this);
        mFacebookCallbackManager = CallbackManager.Factory.create();

        initNaverLogin();
        initFacebookLogin();
        initKakaoLogin();
    }

    @OnClick(R.id.btn_login_naver)
    void naverLoginOnclick(){
        mOAuthLoginModule.startOauthLoginActivity(this, mOAuthLoginHandler);
    }
    @OnClick(R.id.btn_login_facebook)
    void facebookLoginOnclick() {
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
    }

    @OnClick(R.id.btn_login_kakao)
    void kakaoLoginOnclick() {
        Session.getCurrentSession().open(AuthType.KAKAO_TALK, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCurrentSession().removeCallback(mKakaoCallback);
    }

    private void initNaverLogin(){
        mOAuthLoginModule = OAuthLogin.getInstance();
        mOAuthLoginModule.init(
                LoginActivity.this
                ,OAUTH_CLIENT_ID
                ,OAUTH_CLIENT_SECRET
                ,OAUTH_CLIENT_NAME
        );
    }
    private void initFacebookLogin() {
        LoginManager.getInstance().registerCallback(mFacebookCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mLoginModule.loginWithFacebook(loginResult, new LoginService.LoginCallback() {
                    @Override
                    public void success() {
                        moveToMainActivity();
                    }

                    @Override
                    public void error(Throwable throwable) {
                        Log.v("LOGIN ERROR", "LOGINERROR_facebook");
                    }
                });
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                Log.v("LOGIN ERROR", "LOGINERROR_facebook(onError)");
            }
        });
    }

    private void initKakaoLogin() {
        mKakaoCallback = new SessionCallback();
        Session.getCurrentSession().addCallback(mKakaoCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    private void moveToMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    private OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
        @Override
        public void run(boolean success) {
            if (success) {
                String accessToken = mOAuthLoginModule.getAccessToken(LoginActivity.this);
                String refreshToken = mOAuthLoginModule.getRefreshToken(LoginActivity.this);
                long expiresAt = mOAuthLoginModule.getExpiresAt(LoginActivity.this);
                String tokenType = mOAuthLoginModule.getTokenType(LoginActivity.this);

                new RequestApiTask().execute(); //로그인이 성공하면 네이버에 계정값들을 가져온다.

            } else {
                String errorCode = mOAuthLoginModule.getLastErrorCode(LoginActivity.this).getCode();
                String errorDesc = mOAuthLoginModule.getLastErrorDesc(LoginActivity.this);
                //Snackbar.make(view, "errorCode:" + errorCode+ ", errorDesc:" + errorDesc, Snackbar.LENGTH_SHORT).show();
            }
        };
    };

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
            kakaoRequestMe();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }

    private void kakaoRequestMe() {
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
            }

            @Override
            public void onSuccess(UserProfile userProfile) {
                mLoginModule.loginWithKakao(new LoginService.LoginCallback() {
                    @Override
                    public void success() {
                        moveToMainActivity();
                    }
                    @Override
                    public void error(Throwable throwable) {
                        Log.v("LOGIN ERROR", "LOGINERROR_kakao");
                    }
                 });
            }

            @Override
            public void onNotSignedUp() {
                // 로그인되어있지 않습니다
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
            }
        });
    }

    private class RequestApiTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            String url = "https://openapi.naver.com/v1/nid/getUserProfile.xml";
            String at = mOAuthLoginModule.getAccessToken(LoginActivity.this);
            LoginModule.loginWithNaver(mOAuthLoginModule.requestApi(LoginActivity.this, at, url), new LoginService.LoginCallback(){

                @Override
                public void success() {
                    moveToMainActivity();
                }

                @Override
                public void error(Throwable throwable) {
                    //Snackbar.make(LoginActivity.this, "로그인 실패하였습니다. 잠시후 다시 시도해 주세요!!", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        protected void onPostExecute(Void content) {

        }

        private void Pasingversiondata(String data) { // xml 파싱
        }
    }
}
