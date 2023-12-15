package com.subway.railme.login;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}