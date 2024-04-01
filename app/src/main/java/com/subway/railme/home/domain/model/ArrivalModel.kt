package com.subway.railme.home.domain.model
/**
 * btrainSttus열차종류 (급행,ITX,일반,특급)    statnNm 지하철역명    barvlDt   열차도착예정시간 (단위:초)
 */
data class ArrivalModel(
    val subwayId: String?,// 지하철 호선
    val trainLineNm: String?,// 도착지방면
    val statnId: String?,// 지하철역ID
    val statnNm: String?,// 지하철역명
    val btrainSttus: String?,// 열차 종류
    val barvlDt: String?, // 열차 도착 예정 시간
    val arvlMsg2: String?,
    val arvlMsg3: String?,
    val arvlCd: String?
)