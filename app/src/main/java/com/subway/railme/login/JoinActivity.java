// 사용자 회원가입을 위한 페이지
package com.subway.railme.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.subway.railme.databinding.ActivityJoinBinding;

public class JoinActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth; // 파이어베이스 인증
    private DatabaseReference databaseReference; // 실시간 데이터베이스
    private ActivityJoinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼
        firebaseAuth = FirebaseAuth.getInstance(); // 비밀번호를 사용하는 신규 사용자를 위해 추가
        databaseReference = FirebaseDatabase.getInstance().getReference("railme");

        binding.btJoinJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 회원가입 처리 시작
                String Email = binding.etJoinEmail.getText().toString().trim(); // trim() = 공백인 부분을 제거하고 보여줌
                String Password = binding.etJoinPassword.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), JoinUserAccount.class);
                startActivity(intent);
                firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (binding.etJoinEmail.length() > 0 && binding.etJoinPassword.length() > 7 && binding.etJoinPassword.length() < 13) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                        JoinUserAccount account = new JoinUserAccount();
                                        account.setIdToken(firebaseUser.getUid());
                                        account.setEmail(firebaseUser.getEmail());
                                        account.setPassword(Password);
                                        databaseReference.child("joinUserAccount").child(firebaseUser.getUid()).setValue(account);
                                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(JoinActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(JoinActivity.this, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }
}