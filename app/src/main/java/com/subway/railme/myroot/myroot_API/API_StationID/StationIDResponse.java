package com.subway.railme.myroot.myroot_API.API_StationID;

import com.subway.railme.myroot.myroot_API.API_StationID.StationID;

import java.util.List;

public class StationIDResponse {
    private List<StationID> stationList;

    public List<StationID> getStationList() {
        return stationList;
    }

    public void setStationList(List<StationID> stationList) {
        this.stationList = stationList;
    }
}
