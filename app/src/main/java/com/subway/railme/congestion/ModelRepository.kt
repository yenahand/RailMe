package com.subway.railme.congestion

import org.tensorflow.lite.Interpreter

interface ModelRepository {
    suspend fun getModel() :List<Interpreter>
}