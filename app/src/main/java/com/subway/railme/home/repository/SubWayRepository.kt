package com.subway.railme.home.repository

interface SubWayRepository {
    suspend fun getSubwayInfo()
}