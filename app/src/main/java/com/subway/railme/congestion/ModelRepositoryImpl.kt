package com.subway.railme.congestion

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import org.tensorflow.lite.Interpreter
import java.io.File

class ModelRepositoryImpl(private val context: Context) : ModelRepository {
    override suspend fun getModel(): List<Interpreter> {
        val interpreters = mutableListOf<Interpreter>()

        for (i in 1..8) {
            val modelFileName = "line$i/$i+linemodel.tflite"

            try {
                val inputStream = context.assets.open(modelFileName)
                val modelFile = withContext(Dispatchers.IO) {
                    File.createTempFile("model", null, context.cacheDir)
                }
                inputStream.use { input ->
                    modelFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                val interpreter = Interpreter(modelFile)
                interpreters.add(interpreter)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return interpreters
    }
}
