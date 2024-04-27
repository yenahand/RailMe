package com.subway.railme.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.naver.maps.map.MapFragment;
import com.subway.railme.R;
import com.subway.railme.databinding.FragmentMyPageBinding;

import javax.annotation.Nullable;

public class MyPageFragment extends Fragment {

    private FragmentMyPageBinding binding;
    private TextView loginArea;
    private String strNick, strEmail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater, container, false);

         return binding.getRoot();
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginArea = view.findViewById(R.id.tv_loginArea);
        binding.ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MpFavoriteFragment mpFavoriteFragment = new MpFavoriteFragment();
                FragmentManager fragmentManager = getChildFragmentManager();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_mypage);

                if (currentFragment instanceof MpFavoriteFragment) {
                    fragmentManager.beginTransaction()
                            .remove(currentFragment)
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_mypage, mpFavoriteFragment)
                            .commit();
                }
            }
        });
        binding.ibTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MpTimeFragment neverFrag = new MpTimeFragment();
                FragmentManager fragmentManager = getChildFragmentManager();
                Fragment currentView = fragmentManager.findFragmentById(R.id.map);

                if(currentView instanceof MpTimeFragment){
                    fragmentManager.beginTransaction()
                            .remove(currentView)
                            .commit();
                }else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.map,neverFrag)
                            .commit();
                }
            }
        });
    }


}