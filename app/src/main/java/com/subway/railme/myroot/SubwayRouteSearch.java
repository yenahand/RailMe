package com.subway.railme.myroot;

import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.Buffer;

public class SubwayRouteSearch {
    public static void main(String[] args) {
        String key = "8HQEUx0Pb4tEOMkK4DuvM9Wfr+huydyVWlSu7gGc3qU";
        String result = "";

        try {
            URL url = new URL("https://api.odsay.com/v1/api/subwayPath" + key + "");

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bufferedReader.readLine();

            JsonParser jsonParser = new JsonParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject subwayInfoResult = (JSONObject)jsonObject.get("subwayInfoResult");
            JSONObject subwayInfo = (JSONObject)subwayInfoResult.get("subwayInfo");

            JSONArray jsonArray = (JSONArray)subwayInfo.get("jsonArray");

        } catch () {

        }
    }
}
