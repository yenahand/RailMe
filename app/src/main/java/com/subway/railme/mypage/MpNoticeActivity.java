package com.subway.railme.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityMpFavoritesBinding;
import com.subway.railme.databinding.ActivityMpNoticeBinding;

public class MpNoticeActivity extends AppCompatActivity {

    private ActivityMpNoticeBinding binding_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_n = ActivityMpNoticeBinding.inflate(getLayoutInflater());
        setContentView(binding_n.getRoot());

        binding_n.swNotice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {

                } else {

                }
            }
        });
    }
}