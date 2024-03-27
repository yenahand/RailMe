package com.subway.railme.home.domain.repository

import com.subway.railme.Unit.API
import com.subway.railme.home.domain.model.ArrivalModel
import com.subway.railme.home.subwayapi.RetrofitClient

class SubwayRepositoryImpl(
    private val client: RetrofitClient
) : SubWayRepository {
    override suspend fun getSubwayInfo(statnNm:String): List<ArrivalModel>? {
        val response = client.api.getSubway(API, "xml", 0, 5, stationName= statnNm)
        val subwayInfoList = response.rows
        val result = response.result

        val infoResult = subwayInfoList?.map { infoItem ->
            ArrivalModel(
                subwayId = infoItem.subwayId,
                trainLineNm = infoItem.trainLineNm,
                statnId = infoItem.statnId,
                statnNm = infoItem.statnNm,
                btrainSttus = infoItem.btrainSttus,
                barvlDt = infoItem.barvlDt
            )
        }
        return infoResult
    }
}