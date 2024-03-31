package com.subway.railme.myroot.myroot_API.API_Route;

import com.subway.railme.myroot.SubPath;


import java.util.List;

public class RouteResponse {
    private String globalStartName; //출발역
    private String globalEndName; //도착역
    private int globalTravelTime; //전체 운행 소요시간
    private int globalStationCount; // 전체 정차역 수
    private int cashFare; // 현금 요금 (성인 기준)
    private List<SubPath> subPath;

    public String getGlobalStartName() {
        return globalStartName;
    }

    public void setGlobalStartName(String globalStartName) {
        this.globalStartName = globalStartName;
    }

    public String getGlobalEndName() {

        return globalEndName;
    }

    public void setGlobalEndName(String globalEndName) {
        this.globalEndName = globalEndName;
    }

    public int getGlobalTravelTime() {
        return globalTravelTime;
    }

    public void setGlobalTravelTime(int globalTravelTime) {
        this.globalTravelTime = globalTravelTime;
    }

    public int getGlobalStationCount() {
        return globalStationCount;
    }

    public void setGlobalStationCount(int globalStationCount) {
        this.globalStationCount = globalStationCount;
    }

    public int getCashFare() {
        return cashFare;
    }

    public void setCashFare(int cashFare) {
        this.cashFare = cashFare;
    }

    public List<SubPath> getSubPath() {
        return subPath;
    }

    public void setSubPath(List<SubPath> subPath) {
        this.subPath = subPath;
    }


}
