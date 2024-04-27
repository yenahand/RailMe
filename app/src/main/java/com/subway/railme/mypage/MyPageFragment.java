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


  /*      // Fragment를 호스팅하는 Activity에서 Intent를 가져옵니다.
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            // Intent에서 데이터를 가져옵니다.
            strNick = intent.getStringExtra("name");
            strEmail = intent.getStringExtra("Email");

            // 가져온 데이터를 TextView에 설정합니다.
            if (strNick != null && !strNick.isEmpty()) {
                // 닉네임이 있으면 표시합니다.
                loginArea.setText(strNick);
            } else {
                // 닉네임이 없으면 로그인을 해주세요 텍스트 표시
                loginArea.setText("로그인 해주세요");
            }
        } else {
            // Intent 없으면 로그인을 해주세요 텍스트 표시
            loginArea.setText("로그인 해주세요");
        }*/
    }


}