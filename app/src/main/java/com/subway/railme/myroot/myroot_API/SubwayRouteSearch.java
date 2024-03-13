package com.subway.railme.myroot.myroot_API;

import com.subway.railme.myroot.myroot_API.OdsayApiKey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SubwayRouteSearch {
//    public static void main(String[] args) throws IOException {
//        String key = OdsayApiKey.key.toString();
//        String urlInfo = "https://api.odsay.com/v1/api/subwayPath?lang=0&CID=1000&Sopt=1&key=" + URLEncoder.encode(key, "UTF-8");
//        URL url = new URL(urlInfo);
//        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Content-type", "application/json");
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//        StringBuilder stringBuilder = new StringBuilder();
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            stringBuilder.append(line);
//        }
//        bufferedReader.close();
//        connection.disconnect();
//
//        System.out.println(stringBuilder.toString());
//    }
}