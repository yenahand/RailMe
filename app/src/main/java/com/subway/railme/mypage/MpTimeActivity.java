package com.subway.railme.mypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityMpTimeBinding;
import com.subway.railme.home.HomeFragment;

public class MpTimeActivity extends AppCompatActivity {

    private ActivityMpTimeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMpTimeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    //@Override
    //public void sendMessage(String s) {

    //}
}