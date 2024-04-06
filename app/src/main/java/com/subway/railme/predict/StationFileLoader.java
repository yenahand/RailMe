//package com.subway.railme.predict;
//
//import android.content.Context;
//import android.content.res.AssetManager;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StationFileLoader {
//    public static float[] readStationData(Context context, String stationName) { // 입력한 역의 csv 파일 가져오는 메서드
//        try {
//            String csvFilePath = MainActivity.selectedLine + "/" + stationName + ".csv";
//            AssetManager assetManager = context.getAssets();
//            InputStream inputStream = assetManager.open(csvFilePath);
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            List<Float> dataList = new ArrayList<>();
//            while ((line = br.readLine()) != null) {
//                float data = Float.parseFloat(line.trim());
//                dataList.add(data);
//            }
//            br.close();
//            float[] dataArray = new float[dataList.size()];
//            for (int i = 0; i < dataList.size(); i++) {
//                dataArray[i] = dataList.get(i);
//            }
//            return dataArray;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
