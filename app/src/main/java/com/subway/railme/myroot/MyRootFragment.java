package com.subway.railme.myroot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.subway.railme.R;
import com.subway.railme.myroot.myroot_API.API_StationID.StationID;
import com.subway.railme.myroot.myroot_API.API_StationID.StationIDResponse;
import com.subway.railme.myroot.myroot_API.API_StationID.StationIDApi;
import com.subway.railme.myroot.myroot_API.OdsayApiKey;
import com.subway.railme.myroot.myroot_API.API_Route.RouteApi;
import com.subway.railme.myroot.myroot_API.API_Route.RouteResponse;
import com.subway.railme.myroot.myroot_API.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRootFragment extends Fragment {

    private EditText etDepartureTime;
    private EditText etDeparture;
    private EditText etDestination;
    private TextView tvFindResult;
    private Button btFindRoot;

    private String departureStationName;
    private String destinationStationName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_root, container, false);

        etDepartureTime = rootView.findViewById(R.id.et_StarringTime);
        etDeparture = rootView.findViewById(R.id.et_Departure);
        etDestination = rootView.findViewById(R.id.et_Destination);
        tvFindResult = rootView.findViewById(R.id.tv_FindResult);
        btFindRoot = rootView.findViewById(R.id.bt_FindRoot);



        btFindRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick();
            }
        });

        return rootView;
    }

    private void onSearchButtonClick() {
        String departureTime = etDepartureTime.getText().toString();
        String departureStation = etDeparture.getText().toString();
        String destinationStation = etDestination.getText().toString();

        getStationID(departureStation, destinationStation, departureTime);
    }

    // 출발역과 도착역의 StationID 가져옴
    private void getStationID(final String departureStation, final String destinationStation, final String departureTime) {
        StationIDApi stationIDApi = RetrofitClient.getRetrofitInstance().create(StationIDApi.class);
        Call<StationIDResponse> departureCall = stationIDApi.searchStation(OdsayApiKey.key, departureStation, "json");
        departureCall.enqueue(new Callback<StationIDResponse>() {
            @Override
            public void onResponse(Call<StationIDResponse> call, Response<StationIDResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStationList() != null && response.body().getStationList().size() > 0) {
                    StationID departureStationID = response.body().getStationList().get(0);

                    getDestinationStationID(departureStationID.getStationID(), destinationStation, departureTime);
                } else {

                    Toast.makeText(getActivity(), "출발역의 StationID를 가져오는 데 실패했습니다...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StationIDResponse> call, Throwable t) {
                // 호출실패시 에러메시지
                Toast.makeText(getActivity(), "API 호출 실패 : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDestinationStationID(final int departureStationID, final String destinationStation, final String departureTime) {
        StationIDApi stationIDApi = RetrofitClient.getRetrofitInstance().create(StationIDApi.class);
        Call<StationIDResponse> destinationCall = stationIDApi.searchStation(OdsayApiKey.key, destinationStation, "json");
        destinationCall.enqueue(new Callback<StationIDResponse>() {
            @Override
            public void onResponse(Call<StationIDResponse> call, Response<StationIDResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getStationList() != null && response.body().getStationList().size() > 0) {
                    StationID destinationStationID = response.body().getStationList().get(0);
                    // 출발역과 도착역의 StationID를 가져왔으므로 이어서 Route API 호출하기
                    getSubwayPath(departureStationID, destinationStationID.getStationID(), departureTime);
                } else {
                    // API 호출 실패 처리
                    Toast.makeText(getActivity(), "도착역 StationID를 가져오는 데 실패했습니다...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StationIDResponse> call, Throwable t) {
                // 네트워크 오류 등으로 인한 API 호출 실패 시
                Toast.makeText(getActivity(), "API 호출에 실패 : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getSubwayPath(final int departureStationID, final int destinationStationID, final String departureTime) {
        RouteApi routeApi = RetrofitClient.getRetrofitInstance().create(RouteApi.class);
        Call<RouteResponse> call = routeApi.getSubwayPath(OdsayApiKey.key, departureStationID, destinationStationID, 0, "json", 1000, 1);
        call.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(Call<RouteResponse> call, Response<RouteResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RouteResponse routeResponse = response.body();
                    displayShortestPath(routeResponse, departureStationName, destinationStationName);
                } else {
                    // API 호출 실패 처리
                    Toast.makeText(getActivity(), " API 호출에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RouteResponse> call, Throwable t) {
                // 네트워크 오류 등으로 인한 API 호출 실패 시
                Toast.makeText(getActivity(), "API 호출 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //결과창
    private void displayShortestPath(RouteResponse routeResponse, String departureStationName, String destinationStationName) {

        int travelTime = routeResponse.getGlobalTravelTime();
        int stationCount = routeResponse.getGlobalStationCount();
        int cashFare = routeResponse.getCashFare();

        List<SubPath> subPaths = routeResponse.getSubPath();
        StringBuilder pathText = new StringBuilder();

        for (int i = 0; i < subPaths.size(); i++) {
            SubPath pathStep = subPaths.get(i);
            String stationName = pathStep.getStationName();
            String transferInfo = pathStep.getWay();

            if (i > 0) {
                pathText.append(" --> ");
            }

            pathText.append(stationName);
            if (transferInfo != null && !transferInfo.isEmpty()) {
                pathText.append(" (").append(transferInfo).append(")");
            }
        }

        pathText.append("\n\n전체 운행 소요시간: ").append(travelTime).append("분");

        tvFindResult.setText("▶ 출발역: " + departureStationName + "\n▶ 도착역: " + destinationStationName + "\n∘총 소요시간: " + travelTime + "\n∘정차역 수: " + stationCount + "\n∘요금: " + cashFare + "\n∘경로: " + pathText.toString());
    }
}
