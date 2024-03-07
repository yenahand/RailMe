package com.subway.railme.myroot;

public class Station {
    int code;
    int line;
    String name;

    public Station() {
        this.code = 0;
        this.line = 0;
        this.name = null;
    }

    public Station(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
