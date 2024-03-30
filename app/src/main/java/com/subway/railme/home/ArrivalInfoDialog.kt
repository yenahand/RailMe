package com.subway.railme.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import com.subway.railme.R
import com.subway.railme.home.domain.model.ArrivalModel

class ArrivalInfoDialog(
    context: Context,
    private val arrivalModels: List<ArrivalModel>
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_arrival_info)

        val tvArrivalInfo = findViewById<TextView>(R.id.tvArrivalInfo)
        val stringBuilder = StringBuilder()
        arrivalModels.forEach { arrivalModel ->
            stringBuilder.append("지하철 호선: ${arrivalModel.subwayId}\n")
            stringBuilder.append("도착지방면: ${arrivalModel.trainLineNm}\n")
            stringBuilder.append("지하철역ID: ${arrivalModel.statnId}\n")
            stringBuilder.append("지하철역명: ${arrivalModel.statnNm}\n")
            stringBuilder.append("열차 종류: ${arrivalModel.btrainSttus}\n")
            stringBuilder.append("열차 도착 예정 시간: ${arrivalModel.barvlDt}\n\n")
        }
        tvArrivalInfo.text = stringBuilder.toString()
    }
}