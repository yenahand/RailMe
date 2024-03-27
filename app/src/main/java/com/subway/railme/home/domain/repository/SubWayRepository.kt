package com.subway.railme.home.domain.repository

import com.subway.railme.home.domain.model.ArrivalModel

interface SubWayRepository {
    suspend fun getSubwayInfo(statnNm: String):List<ArrivalModel>?
}