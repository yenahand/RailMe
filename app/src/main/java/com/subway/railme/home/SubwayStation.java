package com.subway.railme.home;

import java.util.List;

// SubwayStation.java
public class SubwayStation {
    private String stationName;
    private List<SubwayStation> connectedStations;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<SubwayStation> getConnectedStations() {
        return connectedStations;
    }

    public void setConnectedStations(List<SubwayStation> connectedStations) {
        this.connectedStations = connectedStations;
    }
}
