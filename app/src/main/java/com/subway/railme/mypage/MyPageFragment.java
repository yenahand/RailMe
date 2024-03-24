package com.subway.railme.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.subway.railme.databinding.FragmentMyPageBinding;


public class MyPageFragment extends Fragment {

    private FragmentMyPageBinding binding_;
    private String strNick, strEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding_ = FragmentMyPageBinding.inflate(inflater, container, false);
        return binding_.getRoot();

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strEmail = intent.getStringExtra("Email");

        binding_.tvLoginArea.setText(strNick);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding_ = null;
    }
}