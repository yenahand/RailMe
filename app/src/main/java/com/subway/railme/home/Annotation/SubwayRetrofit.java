/*package com.subway.railme.home.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

 public class SubwayRetrofit {
    public static void main(String[] args) {
        try {
            String key = "59436b514a74706633314b69617558";
            String fileType = "json";
            String serviceName = "realtimeStationArrival";
            String startLocation = "1";
            String endLocation = "10";

            StringBuilder urlBuilder = new StringBuilder("http://data.seoul.go.kr/dataList/OA-15799/A/1/datasetView.do");
            urlBuilder.append("/" + URLEncoder.encode(key, "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(fileType, "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(serviceName, "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(startLocation, "UTF-8"));
            urlBuilder.append("/" + URLEncoder.encode(endLocation, "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/xml");

            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            conn.disconnect();

            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
