package com.subway.railme.home.API;

import com.google.gson.annotations.SerializedName;

public class RealtimeStationArrival {
    @SerializedName("updnLine")
    private String updnLine;

    @SerializedName("trainLineNm")
    private String trainLineNm;

    @SerializedName("statnNm")
    private String statnNm;

    @SerializedName("barvlDt")
    private String barvlDt;

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    public String getTrainLineNm() {
        return trainLineNm;
    }

    public void setTrainLineNm(String trainLineNm) {
        this.trainLineNm = trainLineNm;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public String getBarvlDt() {
        return barvlDt;
    }

    public void setBarvlDt(String barvlDt) {
        this.barvlDt = barvlDt;
    }
}
