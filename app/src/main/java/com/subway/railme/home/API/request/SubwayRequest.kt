package com.subway.railme.home.API.request

data class SubwayRequest(
    val KEY: String,
    val TYPE: String,
    val SERVICE: String,
    val START_INDEX: Int,
    val END_INDEX: Int,
    val statnNm: String
)
