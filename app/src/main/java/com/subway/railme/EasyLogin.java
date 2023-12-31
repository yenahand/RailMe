package com.subway.railme;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class EasyLogin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "641c840c3a49b1f89718aeaa4caf6797")
    }
}