package com.subway.railme.home.repository

import com.subway.railme.home.subwayapi.RetrofitClient

class SubwayRepositoryImpl(
    private val client:RetrofitClient
):SubWayRepository {
    override suspend fun getSubwayInfo() {
        TODO("Not yet implemented")
    }
}