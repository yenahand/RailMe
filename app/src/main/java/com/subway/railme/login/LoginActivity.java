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

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;


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

            }
        });

        binding.ibKakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
    }


}