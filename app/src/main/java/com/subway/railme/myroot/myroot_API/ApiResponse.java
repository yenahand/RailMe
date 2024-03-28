package com.subway.railme.myroot.myroot_API;

import com.google.gson.annotations.SerializedName;
public class ApiResponse {
    @SerializedName("subPath")
    private SubPath[] subPaths;

    @SerializedName("totalTime")
    private String totalTime;

    public SubPath[] getSubPaths() {
        return subPaths;
    }

    public void setSubPaths(SubPath[] subPaths) {
        this.subPaths = subPaths;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}

