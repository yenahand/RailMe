package com.subway.railme.congestion

import android.content.Context
import com.subway.railme.db.PreferenceUtil
import com.subway.railme.predict.TFLiteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import org.tensorflow.lite.Interpreter
import java.io.File

class ModelRepositoryImpl(context: Context, modelPath: String, options: Interpreter.Options) : ModelRepository {
    private val preferenceUtil = PreferenceUtil(context)
    private val stationFileLoader = StationFileLoader
    init {
        // StationFileLoader 초기화
        StationFileLoader.init(context, modelPath, options)
    }
    override suspend fun predict(inputData: FloatArray): Float {
     TODO()
    }
    override suspend fun getCongestionModel(stationName: String, currentDate: String): List<CongestionModel> {
        val stationName = preferenceUtil.getStationInfo("stationName", "")
        val currentTime = preferenceUtil.getTime("currentTime", "")
        // 현재 시간 및 역 이름을 사용하여 딥러닝 모델로 혼잡도를 예측
        val congestionRate = predictCongestion(stationName, currentTime)

        val congestionModel = CongestionModel(congestionRate, stationName, currentTime)
        return listOf(congestionModel)
    }
    private suspend fun predictCongestion(stationName: String, currentTime: String): Float {
        // "+역" 추가
        val inputStationName = stationName + "역"

        // 예측에 사용될 입력 데이터 생성
        val inputData = floatArrayOf(0.0f) // 예시 입력 데이터
        return predict(inputData)
    }
}

