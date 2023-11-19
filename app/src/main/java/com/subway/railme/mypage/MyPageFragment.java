package com.subway.railme.mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subway.railme.R;
import com.subway.railme.databinding.FragmentCongestionBinding;
import com.subway.railme.databinding.FragmentMyPageBinding;


public class MyPageFragment extends Fragment {
    private FragmentMyPageBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}