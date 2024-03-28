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

    //private FragmentMyPageBinding binding_;
    private LinearLayout timelog;
    private LinearLayout favorites;
    private TextView loginArea;
    private String strNick, strEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //binding_ = FragmentMyPageBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        // return binding_.getRoot();

        timelog = view.findViewById(R.id.timelog);
        favorites = view.findViewById(R.id.favorites);

        timelog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPageFragment myPageFragment = new MyPageFragment();
                MpTimeFragment mpTimeFragment = new MpTimeFragment();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_mypage, myPageFragment);
                fragmentTransaction.add(R.id.fragment_mypage, mpTimeFragment);

                fragmentTransaction.commit();
            }
        });

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPageFragment myPageFragment = new MyPageFragment();
                MpFavoritesFragment mpFavoritesFragment = new MpFavoritesFragment();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_mypage, myPageFragment);
                fragmentTransaction.add(R.id.fragment_mypage, mpFavoritesFragment);

                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginArea = view.findViewById(R.id.tv_loginArea);

        // Fragment를 호스팅하는 Activity에서 Intent를 가져옵니다.
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            // Intent에서 데이터를 가져옵니다.
            strNick = intent.getStringExtra("name");
            strEmail = intent.getStringExtra("Email");

            // 가져온 데이터를 TextView에 설정합니다.
            //binding_.tvLoginArea.setText(strNick);
            loginArea.setText(strNick);
        }
    }

    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding_ = null;
    }*/
}