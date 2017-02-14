package com.boostcamp.eunjilee.innerbeauty;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

/**
 * Created by eunjilee on 10/02/2017.
 */

public class InnerBeautyActivity extends Application {

    //TODO; Do not place Android context classes in static fields; this is a memory leak (and also breaks Instant Run)
    private static Activity mCurrentActivity;
    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        FacebookSdk.sdkInitialize(this);
        KakaoSDK.init(new KakaoSDKAdapter());
    }

    public static Application getGlobalApplicationContext() {
        return mApplication;
    }

    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public static void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    private static class KakaoSDKAdapter extends KakaoAdapter {
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[]{AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public boolean isSecureMode() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Activity getTopActivity() {
                    return InnerBeautyActivity.getCurrentActivity();
                }
                @Override
                public Context getApplicationContext() {
                    return InnerBeautyActivity.getGlobalApplicationContext();
                }
            };
        }
    }

}
