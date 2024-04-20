package com.subway.railme.congestion

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.subway.railme.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


data class CongestionModel(
    val congestionRate: Float, // 혼잡률 (0.0 ~ 1.0)
    val stationName: String, // 역 이름
    val currentDate: String // 현재 날짜
) {
    companion object {
        // 혼잡도에 따라 RGB 색상을 반환하는 함수
        fun getColorBasedOnCongestionRate(congestionRate: Float): Int {
            return when {
                congestionRate < 0.75 -> Color.GREEN // 75% 미만: 초록색
                congestionRate < 1.25 -> Color.YELLOW // 75% 이상, 125% 미만: 노란색
                congestionRate < 1.75 -> Color.RED // 125% 이상, 175% 미만: 빨간색
                else -> Color.rgb(139, 0, 0) // 175% 이상: 진한 빨간색
            }
        }

        fun getCurrentDate(): String {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            return dateFormat.format(Date())
        }
        fun getCurrentTime():String{
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            return timeFormat.format(Date())
        }
        fun getCongestionText(congestionRate: Float): String {
            return when {
                congestionRate < 0.75 -> "여유"
                congestionRate < 1.25 -> "보통"
                congestionRate < 1.75 -> "혼잡"
                else -> "매우 혼잡"
            }
        }



    }

    fun getColorResId(context: Context): Int {
        val color = getColorBasedOnCongestionRate(congestionRate)
        return context.resources.getIdentifier("color_$color", "color", context.packageName)
    }


}

