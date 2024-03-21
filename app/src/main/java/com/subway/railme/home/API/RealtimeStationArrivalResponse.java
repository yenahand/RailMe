package com.subway.railme.home.API;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RealtimeStationArrivalResponse {
    @SerializedName("realtimeArrivalList")
    private List<RealtimeStationArrival> realtimeArrivalList;

    public List<RealtimeStationArrival> getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList(List<RealtimeStationArrival> realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }
}
