package com.subway.railme.congestion

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.subway.railme.R
import com.subway.railme.databinding.FragmentCongestionKBinding
import com.subway.railme.db.MyApplication
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.flex.FlexDelegate
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

/**
 * 혼잡도 프래그먼트 코틀린
 */

class CongestionKFragment(
) : Fragment() {
    private var _binding: FragmentCongestionKBinding? = null
    private val binding get() = _binding!!
    private lateinit var stationNameTextView: TextView
    private lateinit var stationName: String
    private lateinit var tfliteModel: TFLiteModel
    private lateinit var datePicker: DatePicker
    private val lookBack = 10
    private var date: String = ""
    private var selectedLine: String? = null // 선택된 호선
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCongestionKBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stationNameTextView = binding.tvCurrentStation

        val lineSpinner = binding.lineSpinner
        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.line_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lineSpinner.adapter = adapter


        lineSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedLine = parentView?.getItemAtPosition(position).toString()

                loadTFLiteModel(selectedLine)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                val selectedLine = parentView?.getItemAtPosition(0).toString()
                loadTFLiteModel(selectedLine)
            }

        }
        loadPreferencesData()
        val savedStationName = MyApplication.prefs.getStationInfo("stationName", "")
        stationName = "$savedStationName 역"
        stationNameTextView.text = stationName
        loadTFLiteModel(stationName)
        binding.ibCongestionInfo.setOnClickListener {
            showCongestionInfoPopup(binding.root)
        }
        binding.predictButton.setOnClickListener {
            if (::tfliteModel.isInitialized) {
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val currentDate = sdf.format(Date())

                val stationData = StationFileLoader.readStationData(
                    requireContext(),
                    savedStationName
                )
                val currentTime = MyApplication.prefs.getTime("currentTime", "")
                val mappedTime = mapTimeToNumber(currentTime)
                val inputData = stationData?.let { preprocessAndScale(it) }
                val dateDiff = calculateDateDiff(currentDate)
                val predictions = ArrayList<Float>()
                inputData?.let {
                    for (i in 0 until dateDiff + mappedTime) {
                        if (i >= lookBack) {
                            val prediction = tfliteModel.predict(it)
                            predictions.add(prediction)
                            System.arraycopy(it, 1, it, 0, it.size - 1)
                            it[it.size - 1] = prediction
                        }
                    }
                }

                val scaledPredictions = FloatArray(predictions.size) { predictions[it] }
                val originalPredictions = stationData?.let { preprocessAndInverseScale(scaledPredictions, it) }
                val lastOriginalPrediction = originalPredictions?.get(originalPredictions.size - 1)

                val congestionRate = lastOriginalPrediction ?: 0f

                /*// 혼잡도 텍스트 계산
                val congestionText = getCongestionText(congestionRate)*/

                // 혼잡도 모델 생성
                val congestionModel = CongestionModel(
                    congestionRate = congestionRate,
                    stationName = savedStationName,
                    currentDate = CongestionModel.getCurrentDate()
                )

                // 혼잡도에 따른 색상 가져오기
                val color = congestionModel.getColorResId(requireContext())
                binding.congestionColor.setBackgroundColor(ContextCompat.getColor(requireContext(), color))

           /*     // 혼잡도 텍스트를 화면에 표시
                binding.resultTextView.text = "혼잡도: $congestionText"*/
            } else {
                Log.e("CongestionKFragment", "tfliteModel is not initialized")
                // 처리할 내용 추가
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun loadPreferencesData() {
        val savedStationName = MyApplication.prefs.getStationInfo("stationName", "")
        val currentTime = MyApplication.prefs.getTime("currentTime", "")
        val currentDate = MyApplication.prefs.getDate("currentDate", "")
        predictCongestion(savedStationName, currentDate, currentTime)
    }
    private fun predictCongestion(stationName: String, date: String, time: String) {
        // CSV 파일에서 역에 대한 데이터 읽기
        val stationData = StationFileLoader.readStationData(requireContext(), stationName)

        // 혼잡도 예측에 필요한 데이터가 유효한지 확인
        if (stationData != null && stationData.isNotEmpty()) {
            // 예측에 사용될 입력 데이터 준비
            val inputData = preprocessInputData(stationData, date, time)

    /*        // 혼잡도 예측하기
            val congestionRate = CongestionModel.getCongestionRateFromModel(requireContext(), getTFLiteModelPath(selectedLine), inputData)

            // 혼잡도 텍스트 가져오기
            val congestionText = CongestionModel.getCongestionText(congestionRate)

            // 혼잡도에 따른 색상 가져오기
            val color = CongestionModel.getColorBasedOnCongestionRate(congestionRate)

            // 결과 텍스트뷰에 혼잡도 값 및 색상 적용
            binding.tvCongestionRate.text = congestionText
            binding.congestionColor.setBackgroundColor(color)*/
        } else {
            // 데이터가 유효하지 않을 경우 예외 처리
            Log.e("CongestionKFragment", "Invalid station data")
            // 오류 처리 또는 사용자에게 메시지 표시
        }
    }
    private fun preprocessInputData(stationData: FloatArray, date: String, time: String): FloatArray {
        // 현재 시간 및 날짜 정보를 입력 데이터에 추가
        val scaledTime = mapTimeToNumber(time)
        val dateDiff = calculateDateDiff(date)
        val inputSize = stationData.size + 2 // 입력 데이터 크기 (역 데이터 + 시간 + 날짜)

        val inputData = FloatArray(inputSize)

        // 입력 데이터의 처음부터 역 데이터 복사
        System.arraycopy(stationData, 0, inputData, 0, stationData.size)

        // 입력 데이터의 마지막에 시간 및 날짜 추가
        inputData[inputSize - 2] = scaledTime.toFloat()
        inputData[inputSize - 1] = dateDiff.toFloat()


        return inputData
    }
    private fun loadTFLiteModel(selectedLine: String?) {
        // 선택된 호선에 따라 적절한 모델 파일 경로 가져오기
        val modelFilePath = getTFLiteModelPath(selectedLine)

        // 모델 파일 경로를 사용하여 모델 로드
        val options = Interpreter.Options()
            .addDelegate(FlexDelegate())
            .setUseXNNPACK(false)
        try {
            tfliteModel = TFLiteModel(requireContext(), modelFilePath, options)
        } catch (e: IllegalArgumentException) {
            // 모델 파일 로드 중 오류 발생 시 예외 처리
            Log.e("CongestionKFragment", "Error loading TensorFlow Lite model", e)
            // 오류 처리 방법에 따라 추가 작업이 필요할 수 있음
        }
    }

    private fun getTFLiteModelPath(selectedLine: String?): String {
        return selectedLine?.let { "$it/model.tflite" } ?: "" // null 체크 추가
    }

    @SuppressLint("SimpleDateFormat")
    private fun calculateDateDiff(date: String): Int {
        return try {
            val currentDate = MyApplication.prefs.getDate("currentDate", "")
            val sdf = SimpleDateFormat("yyyy-MM-dd")

            // inputDate를 datePicker에서 선택한 날짜로 설정
            val inputDate = sdf.parse(currentDate)

            // staticDate는 고정
            val staticDate = sdf.parse("2023-10-31")

            // 입력 날짜와 마지막 날짜 간격 계산
            val dateDiff = maxOf((inputDate.time - staticDate.time) / (1000 * 60 * 60 * 24), 0)
            dateDiff.toInt()
        } catch (e: ParseException) {
            e.printStackTrace()
            0  // 예외 처리 - 날짜 파싱 오류 시 기본값 반환
        }
    }

    private fun mapTimeToNumber(timeStr: String): Int {
        val sdf = SimpleDateFormat("HH:mm")
        return try {
            val timeObj = sdf.parse(timeStr)
            val calendar = Calendar.getInstance()
            calendar.time = timeObj

            val hour = calendar.get(Calendar.HOUR_OF_DAY)

            // 시간대에 따라 숫자로 매핑
            when {
                hour in 4 until 6 -> 1
                hour in 6 until 7 -> 2
                hour in 7 until 8 -> 3
                hour in 8 until 9 -> 4
                hour in 9 until 10 -> 5
                hour in 10 until 11 -> 6
                hour in 11 until 12 -> 7
                hour in 12 until 13 -> 8
                hour in 13 until 14 -> 9
                hour in 14 until 15 -> 10
                hour in 15 until 16 -> 11
                hour in 16 until 17 -> 12
                hour in 17 until 18 -> 13
                hour in 18 until 19 -> 14
                hour in 19 until 20 -> 15
                hour in 20 until 21 -> 16
                hour in 21 until 22 -> 17
                hour in 22 until 23 -> 18
                hour >= 23 || hour < 2 -> 19
                else -> 0  // 예외 처리 - 해당되지 않는 경우
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            0  // 파싱 오류 발생 시 기본값 반환
        }
    }


    // 예측 데이터 전처리 및 스케일링 함수 추가
    private fun preprocessAndScale(stationData: FloatArray): FloatArray {
        // 예측에 사용될 입력 데이터 준비
        val inputData = FloatArray(stationData.size)

        // 읽어온 데이터의 최소값과 최대값을 사용하여 스케일링
        var min = Float.MAX_VALUE
        var max = Float.MIN_VALUE
        for (data in stationData) {
            if (data < min) {
                min = data
            }
            if (data > max) {
                max = data
            }
        }

        for (i in inputData.indices) {
            inputData[i] = (stationData[i] - min) / (max - min) // 스케일링
        }

        return inputData
    }

    private fun preprocessAndInverseScale(
        scaledPredictions: FloatArray,
        originalData: FloatArray
    ): FloatArray {
        var min = Float.MAX_VALUE
        var max = Float.MIN_VALUE
        for (data in originalData) {
            if (data < min) {
                min = data
            }
            if (data > max) {
                max = data
            }
        }

        val originalPredictions = FloatArray(scaledPredictions.size)
        for (i in scaledPredictions.indices) {
            originalPredictions[i] = scaledPredictions[i] * (max - min) + min // 역변환
        }

        return originalPredictions
    }

    // 역 이름이 유효한지를 확인하는 메서드
    private fun isValidStation(selectedLine: String?, stationName: String): Boolean {
        // 역 이름이 비어 있지 않고, 해당 역의 데이터 파일이 있는지 확인
        return !stationName.isEmpty() && hasStationDataFile(selectedLine, stationName)
    }

    private fun hasStationDataFile(selectedLine: String?, stationName: String): Boolean {
        return try {
            // 역 이름에 해당하는 모델 파일 경로
            val modelFilePath = getTFLiteModelPath(selectedLine)
            // 모델 파일이 존재하는지 확인
            val assetManager: AssetManager = this.requireContext().assets
            assetManager.open(modelFilePath).close() // 파일을 열어서 닫기만 함
            true
        } catch (e: IOException) {
            // 파일이 존재하지 않는 경우
            false
        }
    }
}
private fun showCongestionInfoPopup(view: View) {
    val inflater = LayoutInflater.from(view.context)
    val popupView = inflater.inflate(R.layout.popup, null)
    val width = ViewGroup.LayoutParams.WRAP_CONTENT
    val height = ViewGroup.LayoutParams.WRAP_CONTENT
    val focusable = true
    val popupWindow = PopupWindow(popupView, width, height, focusable)
    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    val closeButton = popupView.findViewById<Button>(R.id.popup_back)
    closeButton.setOnClickListener { v: View? -> popupWindow.dismiss() }
}



