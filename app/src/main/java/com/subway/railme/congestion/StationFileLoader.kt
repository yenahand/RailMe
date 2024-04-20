package com.subway.railme.congestion

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.util.Log
import com.subway.railme.MainActivity
import com.subway.railme.db.MyApplication
import okio.IOException
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

object StationFileLoader {
    private lateinit var tflite: Interpreter
    private const val lookBack = 10
    private val inputBuffer = Array(1) { Array(lookBack) { FloatArray(1) } }


    fun init(context: Context, modelPath: String, options: Interpreter.Options) {
        val tfliteModel = loadModelFile(context, modelPath)
        tflite = if (tfliteModel != null) {
            Interpreter(tfliteModel, options)
        } else {
            Log.e("TFLiteModel", "TensorFlow Lite model load error.")
            return
        }
    }

    private fun loadModelFile(context: Context, modelPath: String, selectedLine: String = ""): ByteBuffer? {
        try {
            val fullPath = if (selectedLine.isNotEmpty()) "$selectedLine/$modelPath" else modelPath
            val assetManager = context.assets
            val inputStream: InputStream = assetManager.open(fullPath)
            val modelData = inputStream.readBytes()
            inputStream.close()
            return ByteBuffer.allocateDirect(modelData.size)
                .order(ByteOrder.nativeOrder())
                .put(modelData)
                .apply { rewind() }
        } catch (e: IOException) {
            Log.e("TFLiteModel", "Failed to load TensorFlow Lite model.", e)
            return null
        }
    }
    fun readStationData(context: Context, stationName: String, selectedLine: String): FloatArray? {
        return try {
            val csvFileName = "$selectedLine.csv" // 파일명 구성
            val csvFilePath = "$stationName/$csvFileName" // 경로 구성
            val assetManager = context.assets
            val inputStream = assetManager.open(csvFilePath)
            val br = BufferedReader(InputStreamReader(inputStream))
            val dataList = ArrayList<Float>()
            var line: String?
            while (br.readLine().also { line = it } != null) {
                val data = line?.trim()?.toFloat() ?: continue
                dataList.add(data)
            }
            br.close()
            val dataArray = FloatArray(dataList.size)
            for (i in dataList.indices) {
                dataArray[i] = dataList[i]
            }
            dataArray
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}
