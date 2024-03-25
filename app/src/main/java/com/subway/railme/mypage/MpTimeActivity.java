package com.subway.railme.mypage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityLoginBinding;
import com.subway.railme.databinding.ActivityMainBinding;
import com.subway.railme.databinding.ActivityMpTimeBinding;
import com.subway.railme.home.HomeFragment;

public class MpTimeActivity extends AppCompatActivity {

    private ActivityMpTimeBinding binding_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding_t = ActivityMpTimeBinding.inflate(getLayoutInflater());
        setContentView(binding_t.getRoot());

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.layout.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    //@Override
    //public void sendMessage(String s) {

    //}
}