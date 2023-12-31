package com.subway.railme;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.subway.railme.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding _binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = _binding.getRoot();
        setContentView(view);
        //Component 네비게이션 바텀 하단바 적용
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(_binding.myBottomNav, navController);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _binding = null;
    }


}