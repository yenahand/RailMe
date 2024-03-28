// 사용자 계정 로그인을 위한 페이지
package com.subway.railme.login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.subway.railme.MainActivity;
import com.subway.railme.databinding.ActivityLoginBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/**
 * 로그인 액티비티  바로 켜지게 & 간편 로그인 텍스트만 누르면 그냥 Main으로 넘어감
 * 전체적으로 코드의 모듈화가 좋아 보이네요 카카오 간편 로그인과 파이어베이스 회원가입을 하는걸 따로 분리를 해뒀다는 설명이 필요해 보여요
 */
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;
        firebaseAuth = FirebaseAuth.getInstance(); // Firebase 인증 객체 초기화

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

                /**
                 * 솔지히 이건 필요없지 않나요 파베 안쓰잖아
                 * - 기본 로그인에는 파이어베이스를 이용해서 로그인하는 걸로 이용해서 구현한 것이고,swe 카카오 간편 로그인은 따로 간편 로그인 구동할 수 있게 하려고 둘이 따로 한 것입니다!
                 * 기본 로그인과 카카오 간편 로그인을 같이 하려고 하는것 같군요 그럼 파이어베이스 프로젝트도 tktjrgus11@gmail.com으로 권한줘서 초대 해주세요
                 */
                // Firebase에 이메일과 비밀번호로 로그인 요청
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 로그인 성공 시 MainActivity로 이동
                                    /**
                                     * 카카오 로그인 성공시로 바꿔야하지 않나 싶네요
                                     */
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

        binding.ibKakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    kakaologin();
                } else {
                    accountLogin();
                }
            }
        });

        /**
         * 간편로그인 텍스트 누르면 메인 엑티비티로 넘어감
         */
        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

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

    public void kakaologin() {
        String TAG = "kakaologin()";
        UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,(oAuthToken, error) -> {
            if(error != null) {
                Log.e(TAG, "로그인에 실패하였습니다.", error);
            } /*else if(oAuthTOken != null) {
                Log.i(TAG, "로그인에 성공하였습니다!(토큰): " + oAuthToken.getAccessToken());
                getUserInfo();
            }*/
            return null;
        });
    }

    public void accountLogin() {
        String TAG = "accountLogin()";
        UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,(oAuthToken, error) -> {
            if(error != null) {
                Log.e(TAG, "로그인에 실패하였습니다.", error);
            } else if (oAuthToken != null) {
                Log.i(TAG, "로그인에 성공하였습니다!(토큰): " + oAuthToken.getAccessToken());
            }
            return null;
        });
    }

    public void getUserInfo() {
        String TAG = "getUserInfo()";
        UserApiClient.getInstance().me((user, meError) -> {
            if(meError != null) {
                Log.e(TAG, "사용자 정보 요청에 실패하였습니다.", meError);
            } else {
                System.out.println("로그인 완료되었습니다.");
                Log.i(TAG, user.toString());
                {
                    Log.i(TAG, "사용자 정보 요청에 성공하였습니다." + "\n 이메일:" + user.getKakaoAccount().getEmail());
                }
                Account user1 = user.getKakaoAccount();
                System.out.println("사용자 계정" + user1);
            }
            return null;
        });
    }
}