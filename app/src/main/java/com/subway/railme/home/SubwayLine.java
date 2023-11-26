package com.subway.railme.home;

import java.util.List;


public class SubwayLine {
    private String lineName;
    private List<SubwayStation> stations;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public List<SubwayStation> getStations() {
        return stations;
    }

    public void setStations(List<SubwayStation> stations) {
        this.stations = stations;
    }
}
