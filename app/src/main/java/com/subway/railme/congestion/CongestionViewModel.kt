package com.subway.railme.congestion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CongestionViewModel(
    private val modelRepository: ModelRepository
) : ViewModel() {

    // 혼잡도를 분석하고 결과를 반환하는 메서드
    fun analyzeCongestion(): LiveData<CongestionModel> {
        val resultLiveData = MutableLiveData<CongestionModel>()

        // 모델을 로드하고 혼잡도 분석하는 작업을 실행
        viewModelScope.launch {
            val interpreters = modelRepository.getModel()
            // 적절한 입력 데이터를 생성하고 모델에 전달
            // 모델 결과를 분석하여 혼잡도에 따른 적절한 색상과 텍스트를 가져옴
            // 결과를 LiveData에 설정하여 UI에 표시
        }

        return resultLiveData
    }
}