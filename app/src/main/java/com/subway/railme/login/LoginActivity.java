// 사용자 계정 로그인을 위한 페이지
package com.subway.railme.login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import com.kakao.util.exception.KakaoException;
import com.subway.railme.MainActivity;
import com.subway.railme.databinding.ActivityLoginBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    private Context mContext;
    private final ISessionCallback iSessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            // Kakao 로그인 성공 시 처리
            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                @Override
                public Unit invoke(User user, Throwable throwable) {
                    if (user != null) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "카카오 로그인 성공", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "카카오 사용자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                    }
                    return null;
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Toast.makeText(LoginActivity.this, "카카오 로그인 실패", Toast.LENGTH_SHORT).show();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;

        firebaseAuth = FirebaseAuth.getInstance(); // Firebase 인증 객체 초기화

        // Kakao 로그인 세션에 콜백 객체 등록
        Session.getCurrentSession().addCallback(iSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();



        // 로그인 버튼 클릭 시 동작
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이메일과 비밀번호 가져오기
                String email = binding.etLogin.getText().toString().trim();
                String password = binding.etPassword.getText().toString().trim();

                // 이메일 또는 비밀번호가 비어있는지 확인
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Firebase에 이메일과 비밀번호로 로그인 요청
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 로그인 성공 시 MainActivity로 이동
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // 로그인 실패 시 메시지 출력
                                    Toast.makeText(LoginActivity.this, "이메일 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // 자동 로그인 체크박스 클릭 시 동작
        binding.cbAutologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 자동 로그인 여부에 따라 SharedPreferences에 저장
                SharedPreferencesManager.setBoolean(mContext, "check", isChecked);
            }
        });

        // 회원가입 버튼 클릭 시 동작
        binding.btLoginJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 액티비티로 이동
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}