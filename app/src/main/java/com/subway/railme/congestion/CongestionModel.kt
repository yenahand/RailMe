package com.subway.railme.congestion

import com.subway.railme.R

data class CongestionModel(
    val congestionRate: Float, // 혼잡률 (0.0 ~ 1.0)
    val colorResId: Int, // 색상 리소스 ID
    val text: String // 혼잡도에 따른 텍스트 설명
) {
    companion object {
        // 혼잡도에 따른 색상과 텍스트를 정의한 맵
        val congestionMap = mapOf(
            Pair(0.0f, CongestionModel(0.0f, R.color.congestion_0, "여유")),
            Pair(0.25f, CongestionModel(0.25f, R.color.congestion_25, "보통")),
            Pair(0.5f, CongestionModel(0.5f, R.color.congestion_50, "혼잡")),
            Pair(0.75f, CongestionModel(0.75f, R.color.congestion_75, "매우 혼잡")),
            Pair(1.0f, CongestionModel(1.0f, R.color.congestion_100, "매우 혼잡"))
        )
    }
}
