package com.subway.railme.myroot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subway.railme.R;
import com.subway.railme.databinding.FragmentMyPageBinding;
import com.subway.railme.databinding.FragmentMyRootBinding;


public class MyRootFragment extends Fragment {

    private FragmentMyRootBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyRootBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}