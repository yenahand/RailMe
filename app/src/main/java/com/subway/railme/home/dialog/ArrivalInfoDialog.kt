package com.subway.railme.home.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
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

        val tvStationName = findViewById<TextView>(R.id.tv_stationName)
        val tvArrivalInfo = findViewById<TextView>(R.id.tvArrivalInfo)

        for (i in 0 until minOf(3, arrivalModels.size)) {
            val arrivalModel = arrivalModels[i]
            if (i == 0) {
                // 지하철역명은 위에 따로 표시되도록 햇습니다
                tvStationName.text = "${arrivalModel.statnNm}역"
            }
            val stringBuilder = StringBuilder(tvArrivalInfo.text)
            //stringBuilder.append("지하철 호선: ${arrivalModel.subwayId}\n")
            stringBuilder.append("도착지방면: ${arrivalModel.trainLineNm}\n")
            //stringBuilder.append("지하철역ID: ${arrivalModel.statnId}\n")
            stringBuilder.append("열차 종류: ${arrivalModel.btrainSttus}\n")
            stringBuilder.append("열차 도착 예정 시간: ${arrivalModel.barvlDt}\n\n")
            tvArrivalInfo.text = stringBuilder.toString()
        }

        val btnShutdown = findViewById<Button>(R.id.btn_shutdown)
        btnShutdown.setOnClickListener {
            dismiss() // 닫기버튼
        }
    }
}