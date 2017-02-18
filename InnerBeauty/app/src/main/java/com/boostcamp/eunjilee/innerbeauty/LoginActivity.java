package com.boostcamp.eunjilee.innerbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.kakao.auth.Session.getCurrentSession;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager mFacebookCallbackManager;
    private SessionCallback mKakaoCallback;
    private AccessToken mFacebookToken = null;
    private LoginModule mLoginModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InnerBeautyActivity.setCurrentActivity(this);
        ButterKnife.bind(this);
        mLoginModule = new LoginModule(this);
        mFacebookCallbackManager = CallbackManager.Factory.create();

        initFacebookLogin();
        initKakaoLogin();
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
        super.onActivityResult(requestCode, resultCode, data);
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCurrentSession().removeCallback(mKakaoCallback);
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

    private class SessionCallback implements ISessionCallback {
        @Override
        public void onSessionOpened() {
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
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
        }
    }
}
