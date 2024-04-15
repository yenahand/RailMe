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

    private fun loadModelFile(context: Context, modelPath: String): MappedByteBuffer? {
        try {
            val fileDescriptor: AssetFileDescriptor = context.assets.openFd(modelPath)
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            val fileChannel = inputStream.channel
            val startOffset = fileDescriptor.startOffset
            val declaredLength = fileDescriptor.declaredLength
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        } catch (e: IOException) {
            Log.e("TFLiteModel", "model file load error.", e)
            return null
        }
    }


    fun predict(inputData: FloatArray): Float {
        for (i in 0 until lookBack - 1) {
            inputBuffer[0][i][0] = inputBuffer[0][i + 1][0]
        }
        inputBuffer[0][lookBack - 1][0] = inputData[0]

        val output = Array(1) { FloatArray(1) }
        tflite.run(inputBuffer, output)
        return output[0][0]
    }
    fun readStationData(context: Context, stationName: String): FloatArray? {
        return try {
            val selectedLine = MyApplication.prefs.getStationInfo("stationName","")
            val csvFilePath = "$selectedLine/$stationName.csv"
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
