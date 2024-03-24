package com.subway.railme.myroot;

import com.subway.railme.myroot.myroot_API.Station;

import java.util.ArrayList;

public class Line {
    public static ArrayList<ArrayList<Station>> AllLine; // 전체 모든 호선을 아우르는 Line
    public static ArrayList<Station> line1;
    public static ArrayList<Station> line2;
    public static ArrayList<Station> line3;
    public static ArrayList<Station> line4;
    public static ArrayList<Station> line5;
    public static ArrayList<Station> line6;
    public static ArrayList<Station> line7;
    public static ArrayList<Station> line8;
    public static ArrayList<Station> line9;

    public void line() {
        AllLine = new ArrayList<ArrayList<Station>>();
        line1 = new ArrayList<Station>();
        line2 = new ArrayList<Station>();
        line3 = new ArrayList<Station>();
        line4 = new ArrayList<Station>();
        line5 = new ArrayList<Station>();
        line6 = new ArrayList<Station>();
        line7 = new ArrayList<Station>();
        line8 = new ArrayList<Station>();
        line9 = new ArrayList<Station>();
    }

    public void setLine() {

    }

    public void showLine() {
        for(int i = 0; i < AllLine.size(); i++) {

        }
    }
}
