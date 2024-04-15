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
            val green = (255 * (100 - congestionRate)) / 100
            val red = (255 * congestionRate) / 100
            return Color.rgb(red.toInt(), green.toInt(), 0) // RGB 값으로 색상 생성
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
                congestionRate < 0.25f -> "여유"
                congestionRate < 0.5f -> "보통"
                congestionRate < 0.75f -> "혼잡"
                else -> "매우 혼잡"
            }
        }

    }

    fun getColorResId(context: Context): Int {
        val color = getColorBasedOnCongestionRate(congestionRate)
        return context.resources.getIdentifier("color_$color", "color", context.packageName)
    }


}

