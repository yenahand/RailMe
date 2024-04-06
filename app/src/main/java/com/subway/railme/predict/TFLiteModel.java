package com.subway.railme.predict;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import org.tensorflow.lite.Interpreter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class TFLiteModel {
    private Interpreter tflite;
    private int lookBack = 10;
    private float[][][] inputBuffer = new float[1][lookBack][1];

    // 모델 파일 로드, TensorFlow Lite 인터프리터 초기화
    public TFLiteModel(Context context, String modelPath, Interpreter.Options options) {
        MappedByteBuffer tfliteModel = loadModelFile(context, modelPath);
        if (tfliteModel != null) {
            tflite = new Interpreter(tfliteModel, options);
        } else {
            Log.e("TFLiteModel", "TensorFlow Lite model load error.");
        }
    }

    // 경로로부터 모델 파일 로드
    private MappedByteBuffer loadModelFile(Context context, String modelPath) {
        try {
            AssetFileDescriptor fileDescriptor = context.getAssets().openFd(modelPath);
            FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
            FileChannel fileChannel = inputStream.getChannel();
            long startOffset = fileDescriptor.getStartOffset();
            long declaredLength = fileDescriptor.getDeclaredLength();
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
        } catch (IOException e) {
            Log.e("TFLiteModel", "model file load error.", e);
            return null;
        }
    }

    // 예측하기 위한 메서드 Predict 구현
    public float predict(float[] inputData) {
        for (int i = 0; i < lookBack - 1; i++) {
            inputBuffer[0][i][0] = inputBuffer[0][i + 1][0];
        }
        inputBuffer[0][lookBack - 1][0] = inputData[0];

        float[][] output = new float[1][1];
        tflite.run(inputBuffer, output);
        return output[0][0];
    }
}
