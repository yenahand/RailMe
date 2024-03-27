package com.subway.railme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.subway.railme.home.domain.model.ArrivalModel
import com.subway.railme.home.domain.repository.SubWayRepository
import com.subway.railme.home.domain.repository.SubwayRepositoryImpl
import com.subway.railme.home.subwayapi.RetrofitClient
import kotlinx.coroutines.launch

class SubWayInfoViewModel(
    private val subWayRepository: SubWayRepository
) : ViewModel(){
    private var subWay :String = ""

    private val _searchWay:MutableLiveData<List<ArrivalModel>?> = MutableLiveData()
    val searchWay : LiveData<List<ArrivalModel>?> get() = _searchWay


    init{
        viewModelScope.launch {

        }
    }
        fun setSubwayInfo(statnNm:String){
        subWay = statnNm
        viewModelScope.launch {
            val response = subWayRepository.getSubwayInfo(statnNm)
            _searchWay.value=response
        }
    }

}

class SubWayInfoViewModelFactory():ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubWayInfoViewModel::class.java)){
            return SubWayInfoViewModel(
                SubwayRepositoryImpl(RetrofitClient)
            )as T
        }else{
            throw IllegalArgumentException("Not Found ViewModel Class")
        }
    }
}