package com.subway.railme.congestion

import org.tensorflow.lite.Interpreter

interface ModelRepository {
    suspend fun  predict(inputData: FloatArray): Float

    suspend fun getCongestionModel(stationName: String, currentDate: String): List<CongestionModel>
}
