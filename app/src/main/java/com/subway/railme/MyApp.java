package com.subway.railme;

import android.app.Application;

import com.subway.railme.home.API.RealtimeStationArrivalResponse;
import com.subway.railme.home.API.RealtimeStationArrivalResponseTypeAdapter;
import com.tickaroo.tikxml.TikXml;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TikXml tikXml = new TikXml.Builder()
                .exceptionOnUnreadXml(false)
                .addTypeAdapter(RealtimeStationArrivalResponse.class, new RealtimeStationArrivalResponseTypeAdapter())
                .build();
    }
}
