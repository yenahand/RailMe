package com.subway.railme.home.API.dto

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml



@Xml(name = "realtimeStationArrival")
data class RealtimeStationArrival(
    @Element(name = "RESULT")
    var result: Result? = null,

    @Element(name = "row")
    var rows: List<Row>? = null
)

data class Result(

    @Element(name = "code")
    var code: String? = null,

    @Element(name = "developerMessage")
    var developerMessage: String? = null,

    @Element(name = "link")
    var link: String? = null,

    @Element(name = "message")
    var message: String? = null,

    @Element(name = "status")
    var status: String? = null,

    @Element(name = "total")
    var total: Int? = null
)

data class Row(
    @Element(name = "rowNum")
    var rowNum: Int? = null,

    @Element(name = "selectedCount")
    var selectedCount: Int? = null,

    @Element(name = "totalCount")
    var totalCount: Int? = null,

    @Element(name = "subwayId")
    var subwayId: Int? = null,

    @Element(name = "updnLine")
    var updnLine: String? = null,

    @Element(name = "trainLineNm")
    var trainLineNm: String? = null,

    @Element(name = "statnFid")
    var statnFid: Int? = null,

    @Element(name = "statnTid")
    var statnTid: Int? = null,

    @Element(name = "statnId")
    var statnId: Int? = null,

    @Element(name = "statnNm")
    var statnNm: String? = null,

    @Element(name = "trnsitCo")
    var trnsitCo: Int? = null,

    @Element(name = "ordkey")
    var ordkey: String? = null,

    @Element(name = "subwayList")
    var subwayList: String? = null,

    @Element(name = "statnList")
    var statnList: String? = null,

    @Element(name = "btrainSttus")
    var btrainSttus: String? = null,

    @Element(name = "barvlDt")
    var barvlDt: Int? = null,

    @Element(name = "btrainNo")
    var btrainNo: String? = null,

    @Element(name = "bstatnId")
    var bstatnId: Int? = null,

    @Element(name = "bstatnNm")
    var bstatnNm: String? = null,

    @Element(name = "recptnDt")
    var recptnDt: String? = null,

    @Element(name = "arvlMsg2")
    var arvlMsg2: String? = null,

    @Element(name = "arvlMsg3")
    var arvlMsg3: String? = null,

    @Element(name = "arvlCd")
    var arvlCd: Int? = null
                                                                                                                                                                                                                                                                ) {
}