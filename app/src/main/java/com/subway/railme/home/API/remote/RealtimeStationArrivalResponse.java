package com.subway.railme.home.API.remote;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml(name = "response")
public class RealtimeStationArrivalResponse {
    @Element(name = "realtimeArrivalList")
    private List<RealtimeStationArrival> realtimeArrivalList;

    public List<RealtimeStationArrival> getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList(List<RealtimeStationArrival> realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }
}