// 사용자 계정 로그인을 위한 페이지
package com.subway.railme.login;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.MutableData;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.subway.railme.databinding.ActivityLoginBinding;
import com.subway.railme.databinding.FragmentMyPageBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

// 오류 수정 예정
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvloginArea.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvloginArea.
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 아이디와 패스워드를 입력한 후 로그인 버튼을 클릭했을 때 값을 받아온다는 의미임
                String ID = binding.etLogin.getText().toString();
                String Password = binding.etPassword.getText().toString();

                Intent intent = new Intent(LoginActivity.this, LoginResult.class);
                Intent.putExtra("ID", ID);
                Intent.putExtra("Password", Password);
                startActivity(intent);
            }
        });

        // 입력한 값이 올바른지 확인
        binding.etLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Log.d("SENTI", s + "," + count);
                if(s != null) {
                    inputID = s.toString();
                    binding.etLogin.setClickable(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Log.d("SENTI", s + "," + count);
                if(s != null) {
                    inputPassword = s.toString();
                    binding.etPassword.setClickable(validation());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btLoginJoin.setOnClickListener(new View.OnClickListener() { // 회원가입 버튼 눌렀을 때 화면 전환하기
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        binding.ibKakaoLogin.setOnClickListener(new View.OnClickListener() { // 카카오 로그인 버튼 눌렀을 때
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                } else {
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });
    }

    public class AutoLogin extends ViewModel() {
        MutableData<String> binding.tvloginArea = new MutableLiveData<>();
        private String
    }

    public boolean validation() {
        return inputID.equals(IDOK) && inputPassword.equals(PasswordOK);
    }

    // 카카오톡이 설치되어 있는지 확인하는 메서드 (카카오에서 제공 콜백 객체를 이용)
    Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
        @Override
        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
            // 이때 토큰이 전달되면 로그인 성공, 토큰이 전달되지 않는다면 로그인 실패
            if (oAuthToken != null) {

            }
            if (throwable != null) {

            }
            updateKakaoLoginUi();
            return null;
        }
    };

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
                    // 로그인 되어 있지 않을때
                    Log.d(TAG, "로그인이 되어 있지 않습니다. 다시 한번 확인해 주시길 바랍니다.");
                }
                return null;
            }
        });
    }
}