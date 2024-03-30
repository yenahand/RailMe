package com.subway.railme.home.domain.repository


import android.util.Log
import com.subway.railme.unit.Unit.API
import com.subway.railme.home.domain.dto.RealtimeStationArrival
import com.subway.railme.home.domain.model.ArrivalModel
import com.subway.railme.home.subwayapi.RetrofitClient
import retrofit2.Response

class SubwayRepositoryImpl(
    private val client: RetrofitClient,

) : SubWayRepository {
    override suspend fun getSubwayInfo(statnNm: String): List<ArrivalModel>? {
        try {

            val response: Response<RealtimeStationArrival> = client.api.getSubway(API,
                stationName = statnNm)
            return if (response.isSuccessful) {
                val realtimeStationArrival: RealtimeStationArrival? = response.body()
                Log.d("SSH", "Response: $realtimeStationArrival")
                realtimeStationArrival?.realtimeArrivalList?.map { infoItem ->
                    ArrivalModel(
                        subwayId = infoItem.subwayId,
                        trainLineNm = infoItem.trainLineNm,
                        statnId = infoItem.statnId,
                        statnNm = infoItem.statnNm,
                        btrainSttus = infoItem.btrainSttus,
                        barvlDt = infoItem.barvlDt,
                        arvlMsg2 = infoItem.arvlMsg2,
                        arvlMsg3 = infoItem.arvlMsg3,
                        arvlCd = infoItem.arvlCd
                    )
                } ?: emptyList() // 빈 목록을 반환합니다.
            } else {
                Log.e("SSH", "Request failed with code: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("SSH", "Exception occurred: ${e.message}", e)
            return null
        }
    }
}