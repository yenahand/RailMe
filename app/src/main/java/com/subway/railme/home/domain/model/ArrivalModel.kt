package com.subway.railme.home.domain.model

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