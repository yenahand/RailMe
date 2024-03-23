package com.subway.railme.home.API;

import com.tickaroo.tikxml.annotation.Attribute;
import com.tickaroo.tikxml.annotation.Xml;

@Xml
public class RealtimeStationArrival {
    @Attribute(name = "updnLine")
    private String updnLine;

    @Attribute(name = "trainLineNm")
    private String trainLineNm;

    @Attribute(name = "statnNm")
    private String statnNm;

    @Attribute(name = "barvlDt")
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
