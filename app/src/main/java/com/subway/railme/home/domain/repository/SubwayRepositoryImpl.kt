package com.subway.railme.home.domain.repository


import com.subway.railme.home.API.dto.RealtimeStationArrival
import com.subway.railme.home.domain.model.ArrivalModel
import com.subway.railme.home.subwayapi.RetrofitClient
import retrofit2.Response

class SubwayRepositoryImpl(
    private val client: RetrofitClient,

) : SubWayRepository {
    override suspend fun getSubwayInfo(statnNm: String): List<ArrivalModel>? {
        try {

            val response: Response<RealtimeStationArrival> = client.api.getSubway(stationName = statnNm)
            return if (response.isSuccessful) {
                val realtimeStationArrival: RealtimeStationArrival? = response.body()

                // null 처리를 수행합니다.
                realtimeStationArrival?.items?.map { infoItem ->
                    ArrivalModel(
                        subwayId = infoItem.subwayId,
                        trainLineNm = infoItem.trainLineNm,
                        statnId = infoItem.statnId,
                        statnNm = infoItem.statnNm,
                        btrainSttus = infoItem.btrainSttus,
                        barvlDt = infoItem.barvlDt
                    )
                } ?: emptyList() // 빈 목록을 반환합니다.
            } else {
                null // 요청이 실패한 경우 null을 반환합니다.
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}