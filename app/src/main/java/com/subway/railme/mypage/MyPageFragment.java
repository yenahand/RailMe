package com.subway.railme.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.subway.railme.MainActivity;
import com.subway.railme.databinding.FragmentMyPageBinding;


public class MyPageFragment extends Fragment {

    private FragmentMyPageBinding binding_;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding_ = FragmentMyPageBinding.inflate(inflater, container, false);
        return binding_.getRoot();


    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding_ = null;
    }


}