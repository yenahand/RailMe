package com.subway.railme.mypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
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

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout.frameLayout, HomeFragment());
        fragmentTransaction.commit();

        binding.bttimelog.setOnClickListener(new View.OnClickListener() {
            // fragmentTransaction = getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.layout.frameLayout, HomeFragment);
            fragmentTransaction.commit()
        });
    }

    @Override
    public void SearchData() {

    }

}