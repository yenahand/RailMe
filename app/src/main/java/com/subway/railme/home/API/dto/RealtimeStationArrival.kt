package com.subway.railme.home.API.dto



data class RealtimeStationArrival(
    val list_total_count: Int,
    val RESULT: Result,
    val items: List<RealtimeStationArrivalItem>
)
data class Result(
    val CODE: String,
    val MESSAGE: String
)

data class RealtimeStationArrivalItem(
    val subwayId: String,
    val updnLine: Int,
    val trainLineNm: String,
    val statnFid: String,
    val statnTid: String,
    val statnId: String,
    val statnNm: String,
    val trnsitCo: Int,
    val ordkey: String,
    val subwayList: String,
    val statnList: String,
    val btrainSttus: String,
    val barvlDt: Int,
    val btrainNo: String,
    val bstatnId: String,
    val bstatnNm: String,
    val recptnDt: String,
    val arvlMsg2: String,
    val arvlMsg3: String,
    val arvlCd: Int
)