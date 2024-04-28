package com.subway.railme.login;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;




public class GlobalApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 카카오 SDK V2
         */
        String kakaoAppkey = "8c38182120bfba3a8e9b9cc350ff986b";
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if (ai.metaData != null) {
                kakaoAppkey = ai.metaData.getString("com.kakao.sdk.AppKey");
            }
        } catch (PackageManager.NameNotFoundException e) {
            // if we can't find it in the manifest, just return null
        }

    }


}