package com.subway.railme.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityMpFavoritesBinding;
import com.subway.railme.databinding.ActivityMpTimeBinding;

public class MpFavoritesActivity extends AppCompatActivity {

    private ActivityMpFavoritesBinding binding_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_f = ActivityMpFavoritesBinding.inflate(getLayoutInflater());
        setContentView(binding_f.getRoot());


    }
}