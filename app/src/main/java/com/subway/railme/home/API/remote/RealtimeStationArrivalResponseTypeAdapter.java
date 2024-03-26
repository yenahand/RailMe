package com.subway.railme.home.API.remote;

import com.tickaroo.tikxml.TikXmlConfig;
import com.tickaroo.tikxml.XmlReader;
import com.tickaroo.tikxml.XmlWriter;
import com.tickaroo.tikxml.typeadapter.TypeAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class RealtimeStationArrivalResponseTypeAdapter {}/*implements TypeAdapter<RealtimeStationArrivalResponse> {
    @Override
    public RealtimeStationArrivalResponse fromXml(XmlReader reader) throws IOException {
        RealtimeStationArrivalResponse response = new RealtimeStationArrivalResponse();
        List<RealtimeStationArrival> arrivals = new ArrayList<>();

        // "response" 요소로 이동
        reader.beginElement();
        // "response" 요소 내에서 반복
        while (reader.hasNext()) {
            // "realtimeArrivalList" 요소로 이동
            if (reader.beginElement("realtimeArrivalList")) {
                RealtimeStationArrival arrival = new RealtimeStationArrival();
                // 요소 내 데이터를 가져와서 객체에 설정
                arrival.setStatnNm(reader.next("statnNm").toString());
                arrival.setUpdnLine(reader.next("updnLine").toString());
                arrival.setTrainLineNm(reader.next("trainLineNm").toString());
                arrival.setBarvlDt(reader.next("barvlDt").toString());
                arrivals.add(arrival);
                reader.endElement(); // "realtimeArrivalList" 요소 닫기
            }
        }
        reader.endElement(); // "response" 요소 닫기

        response.setRealtimeArrivalList(arrivals);
        return response;
    }

    @Override
    public void toXml(XmlWriter writer, RealtimeStationArrivalResponse value) throws IOException {
        writer.beginElement("response");
        for (RealtimeStationArrival arrival : value.getRealtimeArrivalList()) {
            writer.beginElement("realtimeArrivalList");
            writer.beginElement("statnNm").textContent(arrival.getStatnNm()).endElement();
            writer.beginElement("updnLine").textContent(arrival.getUpdnLine()).endElement();
            writer.beginElement("trainLineNm").textContent(arrival.getTrainLineNm()).endElement();
            writer.beginElement("barvlDt").textContent(arrival.getBarvlDt()).endElement();
            writer.endElement();
        }
        writer.endElement();
    }

    @Override
    public RealtimeStationArrivalResponse fromXml(XmlReader reader, TikXmlConfig config) throws IOException {
        return null;
    }

    @Override
    public void toXml(XmlWriter writer, TikXmlConfig config, RealtimeStationArrivalResponse value, String overridingXmlElementTagName) throws IOException {

    }
}*/