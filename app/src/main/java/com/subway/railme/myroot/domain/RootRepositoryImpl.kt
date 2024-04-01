package com.subway.railme.myroot.domain

import com.subway.railme.home.subwayapi.RetrofitClient
import com.subway.railme.myroot.model.TransitModel

class RootRepositoryImpl(
    private val client: RetrofitClient,
):RootRepository {
    override suspend fun getTransit(): List<TransitModel> {
        TODO("Not yet implemented")
    }
}