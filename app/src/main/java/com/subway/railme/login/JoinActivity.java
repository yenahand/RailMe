// 사용자 회원가입을 위한 페이지
package com.subway.railme.login;

import static android.os.Build.ID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.subway.railme.databinding.ActivityJoinBinding;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;

// 추가 작성 예정
public class JoinActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityJoinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance(); // 비밀번호를 사용하는 신규 사용자를 위해 추가
        

        binding.btJoinJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final String ID = binding.etJoinId.getText().toString(); 변수명을 추가하던 XML을 추가하던 하나만 하세요
                final String Password = binding.etJoinPassword.getText().toString();
                final String Nickname = binding.etJoinNickname.getText().toString();
//                Intent intent = new Intent(getApplicationContext(), JoinRequest.class);
//                startActivity(intent);
            }

//            if(binding.etJoinId.equals("") || binding.etJoinPassword.equals("") || binding.etJoinNickname.equals("")) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
//                dialog = builder.setMessage("입력하지 않은 칸이 존재합니다. 확인해 주세요.").setNegativeButton("확인", null).create();
//                dialog.show();
//                return;
//            }

//            Response.Listener<String>responseListener=new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try{
//                        JSONObject jsonObject = new JSONObject(response);
//                        boolean success = jsonObject.getBoolean("success");
//
//                        if(success) { // 회원가입 성공시
//                            Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                        } else { // 회원가입 실패시
//                            Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    } catch(JSONException e){
//                        e.printStackTrace();
//                    }
//                }
//            };

//            JoinRequest joinRequest = new JoinRequest(ID, Password, Nickname, responseListener);
            // RequestGueue queue = Volley.newRequestGueue(JoinRequest.this);
            // queue.add(JoinRequest);
        });
    }
    @Override
    public void onStart() {
        super.onStart();
//        FirebaseUser LoginUser = mAuth.getLoginUser();
    }
}