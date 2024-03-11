package com.subway.railme.myroot;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subway.railme.R;
import com.subway.railme.databinding.FragmentMyPageBinding;
import com.subway.railme.databinding.FragmentMyRootBinding;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MyRootFragment extends Fragment {

    private FragmentMyRootBinding binding_r;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding_r = FragmentMyRootBinding.inflate(inflater, container, false);
        return binding_r.getRoot();

        binding_r.btFindRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AssetManager assetManager = getContext().getAssets();

                try {
                    InputStream inputStream = assetManager.open();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuffer buffer = new StringBuffer();
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        buffer.append(line + "\n");
                        line = bufferedReader.readLine();
                    }
                    String jsonData = buffer.toString();

                    //   binding_r.tvFindResult.setText("출발역: " + startName + "\n" + "도착역: " + endName + "\n" + "총 역 수: " + stationCount + "개 역 경유\n" + "총 시간: " + travelTime + "분 소요\n" + "빠른 환승: " + fastDoor + "\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding_r = null;
    }
}