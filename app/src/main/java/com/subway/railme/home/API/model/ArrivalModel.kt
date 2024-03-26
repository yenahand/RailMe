package com.subway.railme.home.API.model

data class ArrivalModel(
    val result: ResultModel?,
    val rows: List<RowModel>?
)

data class ResultModel(
    val code: String?,
    val developerMessage: String?,
    val link: String?,
    val message: String?,
    val status: String?,
    val total: Int?
)

data class RowModel(
    val rowNum: Int?,
    val selectedCount: Int?,
    val totalCount: Int?,
    val subwayId: Int?,
    val updnLine: String?,
    val trainLineNm: String?,
    val statnFid: Int?,
    val statnTid: Int?,
    val statnId: Int?,
    val statnNm: String?,
    val trnsitCo: Int?,
    val ordkey: String?,
    val subwayList: String?,
    val statnList: String?,
    val btrainSttus: String?,
    val barvlDt: Int?,
    val btrainNo: String?,
    val bstatnId: Int?,
    val bstatnNm: String?,
    val recptnDt: String?,
    val arvlMsg2: String?,
    val arvlMsg3: String?,
    val arvlCd: Int?
)
