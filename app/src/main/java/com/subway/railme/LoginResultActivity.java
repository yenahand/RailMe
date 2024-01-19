package com.subway.railme;

import static android.os.Build.ID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

// 오류 수정 예정
import com.subway.railme.databinding.ActivityJoinBinding;


// 로그인에서 입력 받은 값 요청 받는 클래스
// 바인딩을 쓰는데 왜 findviewbtid를 쓰는지 알려주실수있나요
public class LoginResultActivity extends AppCompatActivity {

    private ActivityJoinBinding binding;//여기도 이상한 xml 바인딩 되어있길래 바꿨습니다. 확인해서 수정해주세요
    EditText EditText_get;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.etJoinId.setText(ID + "|"); // 이런식으로 수정해주세요

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String UseId = bundle.getString("ID");
        String password = bundle.getString("Password");

        binding.etJoinPassword.setText(ID + "|");

    }
}
