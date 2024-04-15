package com.subway.railme.congestion

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.subway.railme.db.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tensorflow.lite.Interpreter

class CongestionViewModel(
    private val modelRepository: ModelRepository
) : ViewModel() {

    private val _congestionModel : MutableLiveData <List<CongestionModel>> = MutableLiveData()
    val congestionModel: LiveData<List<CongestionModel>> get() = _congestionModel

    // 혼잡도를 가져오는 함수
    fun fetchCongestionModel(stationName: String, currentDate: String) {
        viewModelScope.launch {
            val model = withContext(Dispatchers.IO) {
                modelRepository.getCongestionModel(stationName, currentDate)
            }
            _congestionModel.value = model
        }
    }
    // 혼잡도에 따른 색상을 가져오는 함수
    fun getCongestionColor(context: Context): Int {
        val congestionRate = _congestionModel.value?.firstOrNull()?.congestionRate ?: 0.0f
        return CongestionModel.getColorBasedOnCongestionRate(congestionRate)
    }
}
class CongestionViewModelFactory(
    private val context: Context,
    private val modelPath: String,
    private val options: Interpreter.Options
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CongestionViewModel::class.java)){
            return CongestionViewModel(
                ModelRepositoryImpl(context, modelPath, options)
            )as T
        }else{
            throw IllegalArgumentException("Not Found ViewModel Class")
        }

    }
}