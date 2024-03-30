package com.subway.railme;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.subway.railme.databinding.ActivityMainBinding;
import com.subway.railme.databinding.FragmentMyPageBinding;
import com.subway.railme.mypage.MyPageFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding _binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = _binding.getRoot();
        setContentView(view);
        Log.d("GetKey", getKeyHash());

        //Component 네비게이션 바텀 하단바 적용
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(_binding.myBottomNav, navController);


    }

    private String getKeyHash() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            if (packageInfo == null) return null;
            for (Signature signature : packageInfo.signatures) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), Base64.NO_WRAP);
                } catch (NoSuchAlgorithmException e) {
                    Log.w("getKeyHash", "Unable to get MessageDigest. signature=" + signature, e);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("getPackageInfo", "Unable to getPackageInfo");
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        _binding = null;
    }
}