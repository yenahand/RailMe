package com.subway.railme.myroot.model

data class TransitModel(
    val CID: Int,
    val stationName: String, //정류장 이름 검색
    val cityName: String,
    val busNo: String
)
