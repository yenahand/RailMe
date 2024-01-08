package com.subway.railme;

import static android.os.Build.ID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

// 오류 수정 예정
import com.subway.railme.databinding.ActivityLoginResultBinding;

// 로그인에서 입력 받은 값 요청 받는 클래스
public class LoginResultActivity extends AppCompatActivity {

    private ActivityLoginResultBinding binding;
    EditText EditText_get;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        EditText_get = findViewById(R.id.EditText_get);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        String UseId = bundle.getString("ID");
        String password = bundle.getString("Password");

        EditText_get.setText(ID + "|" + Password);

    }
}
