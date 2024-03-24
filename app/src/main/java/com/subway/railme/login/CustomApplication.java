package com.subway.railme.login;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;

public class CustomApplication extends Application {
    private static volatile KakaoSDKAdapter instance = null;

    private static class KakaoSDKAdapter extends KakaoAdapter{
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                    @Override
                    public AuthType[] getAuthTypes() {
                        return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
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

            @Override
            public IApplicationConfig getApplicationConfig() {
                return new IApplicationConfig() {
                    @Override
                    public Context getApplicationContext() {
                        return CustomApplication.getGlobalApplicationContext();
                    }
                };
            }
        }

        public static CustomApplication getGlobalApplicationContext() {
            if(instance == null) {
                throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
            }
            return instance;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            instance = this;

            KakaoSDK.init(new KakaoSDKAdapter());
        }

        @Override
        public void onTerminate() {
            super.onTerminate();
            instance = null;
        }
    }
}