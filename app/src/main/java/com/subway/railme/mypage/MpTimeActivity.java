package com.subway.railme.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityLoginBinding;
import com.subway.railme.databinding.ActivityMainBinding;
import com.subway.railme.databinding.ActivityMpTimeBinding;

public class MpTimeActivity extends AppCompatActivity {

    private ActivityMpTimeBinding binding_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_t = ActivityMpTimeBinding.inflate(getLayoutInflater());
        setContentView(binding_t.getRoot());


    }
}