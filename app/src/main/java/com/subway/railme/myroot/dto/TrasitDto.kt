package com.subway.railme.myroot.dto

data class TrasitDto(
    val result: Result
)

data class Result(
    val station: List<Station>,
    val totalCityList: TotalCityList,
    val totalCount: Int
)

data class Station(
    val CID: Int,
    val arsID: String,
    val businfo: List<Businfo>,
    val cityName: String,
    val `do`: String,
    val dong: String,
    val ebid: String,
    val gu: String,
    val localStationID: String,
    val stationClass: Int,
    val stationDirectionName: String,
    val stationID: Int,
    val stationName: String,
    val x: Double,
    val y: Double
)

data class Businfo(
    val busClass: String,
    val busLocalBlID: String,
    val busNo: String
)

data class TotalCityList(
    val includeCity: List<IncludeCity>
)

data class IncludeCity(
    val CID: Int,
    val cityName: String
)