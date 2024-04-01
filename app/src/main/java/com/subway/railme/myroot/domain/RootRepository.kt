package com.subway.railme.myroot.domain


import com.subway.railme.myroot.model.TransitModel

interface RootRepository {
    suspend fun getTransit():List<TransitModel>
}