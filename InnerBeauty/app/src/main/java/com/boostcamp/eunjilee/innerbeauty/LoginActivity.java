package com.boostcamp.eunjilee.innerbeauty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.boostcamp.eunjilee.innerbeauty.model.UserModel;
import com.boostcamp.eunjilee.innerbeauty.module.LoginModule;
import com.boostcamp.eunjilee.innerbeauty.service.LoginService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    CallbackManager mFacebookCallbackManager;
    private AccessToken mFacebookToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mFacebookCallbackManager = CallbackManager.Factory.create();

        initFacebookLogin();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btn_login_facebook)
    void facebookLoginOnclick(View v) {
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
    }

    private void initFacebookLogin() {
        if(mFacebookToken == null){
            final LoginModule loginModule = new LoginModule();
            LoginManager.getInstance().registerCallback(mFacebookCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        mFacebookToken = loginResult.getAccessToken();
                        loginWithFacebook(); // TODO: LoginModule로 옮기기 with 콜백함수
                        moveToMainActivity();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
        }else{
            loginWithFacebook();
            moveToMainActivity();
        }
    }
    private void loginWithFacebook(){
        GraphRequest graphRequest = GraphRequest.newMeRequest(
                mFacebookToken, new GraphRequest.GraphJSONObjectCallback() {
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
    void moveToMainActivity(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }
}
