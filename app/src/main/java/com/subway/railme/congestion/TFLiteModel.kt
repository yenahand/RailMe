package com.subway.railme.congestion

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.util.Log
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TFLiteModel(private val context: Context, private val modelPath: String, private val options: Interpreter.Options) {
    private val tflite: Interpreter
    private val lookBack = 10
    private val inputBuffer = Array(1) { Array(lookBack) { FloatArray(1) } }

    init {
        val tfliteModel = loadModelFile(context, modelPath)
        tflite = if (tfliteModel != null) {
            Interpreter(tfliteModel, options)
        } else {
            Log.e("TFLiteModel", "TensorFlow Lite model load error.")
            throw IllegalArgumentException("TensorFlow Lite model load error.")
        }
    }

    private fun loadModelFile(context: Context, modelPath: String): MappedByteBuffer? {
        return try {
            val fileDescriptor: AssetFileDescriptor = context.assets.openFd(modelPath)
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            val fileChannel: FileChannel = inputStream.channel
            val startOffset: Long = fileDescriptor.startOffset
            val declaredLength: Long = fileDescriptor.declaredLength
            fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength) as MappedByteBuffer
        } catch (e: IOException) {
            Log.e("TFLiteModel", "model file load error.", e)
            null
        }
    }

    fun predict(inputData: FloatArray?): Float {
        for (i in 0 until lookBack - 1) {
            inputBuffer[0][i][0] = inputBuffer[0][i + 1][0]
        }
        inputBuffer[0][lookBack - 1][0] = (inputData ?: 0f) as Float

        val output = Array(1) { FloatArray(1) }
        tflite.run(inputBuffer, output)
        return output[0][0]
    }
}