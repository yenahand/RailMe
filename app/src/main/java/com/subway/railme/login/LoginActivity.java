// 사용자 계정 로그인을 위한 페이지
package com.subway.railme.login;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;
import com.subway.railme.MainActivity;
import com.subway.railme.databinding.ActivityLoginBinding;
import com.subway.railme.databinding.FragmentMyPageBinding;
import com.subway.railme.mypage.MyPageFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FragmentMyPageBinding binding_;
    private FirebaseAuth firebaseAuth; // 파이어베이스 인증
    private DatabaseReference databaseReference; // 실시간 데이터베이스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyPageFragment myPageFragment = new MyPageFragment(); // 프래그먼트와 연결을 위해 프래그먼트 선언
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼
        firebaseAuth = FirebaseAuth.getInstance(); // firebaseAuth의 인스턴스를 가져옴

        binding.btLogin.setOnClickListener(new View.OnClickListener() { // 로그인 버튼 눌렀을 때
            @Override
            public void onClick(View v) {
                String Email = binding.etLogin.getText().toString().trim();
                String Password = binding.etPassword.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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
                } else {
                    // 로그인 되어 있지 않을때
                    Log.d(TAG, "로그인이 되어 있지 않습니다. 다시 한번 확인해 주시길 바랍니다.");
                }
                return null;
            }
        });
    }
}