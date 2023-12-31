package com.subway.railme.login;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.subway.railme.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    private View loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.ib_kakologin);


        // 카카오톡이 설치되어 있는지 확인하는 메서드 (카카오 제공 콜백 객체 이용)
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                // 이때 토큰이 전달이 되면 로그인이 성공한 것이고 토큰이 전달되지 않았다면 로그인 실패
                updateKakaoLoginUi();
                return null;
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });
        updateKakaoLoginUi();
    }

    private void updateKakaoLoginUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                // 로그인이 되어있으면
                if (user != null) {
                    // 유저 아이디
                    Log.d(TAG, "invoke: id" + user.getId());
                    // 유저 이메일
                    Log.d(TAG, "invoke: email" + user.getKakaoAccount().getEmail());
                    // 유저 닉네임
                    Log.d(TAG, "invoke: nickname" + user.getKakaoAccount().getProfile().getNickname());
                    // 유저 성별
                    Log.d(TAG, "invoke: gerder" + user.getKakaoAccount().getGender());
                    // 유저 나이
                    Log.d(TAG, "invoke: age" + user.getKakaoAccount().getAgeRange());
                } else {
                    // 로그인이 되어 있지 않을때
                    Log.d(TAG, "로그인 되어있지 않습니다. 다시 한번 확인해 주시길 바랍니다.");
                }
                return null;
            }
        });
    }
}