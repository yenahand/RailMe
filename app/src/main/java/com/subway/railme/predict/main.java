//package com.subway.railme.predict;
//
//import android.content.res.AssetManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.TimePicker;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.tensorflow.lite.Interpreter;
//import org.tensorflow.lite.flex.FlexDelegate;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//public class main extends AppCompatActivity {
//
//    private TFLiteModel tfliteModel;
//    private EditText editTextStationName;
//    private DatePicker datePicker;
//    private int lookBack = 10;
//    private String input_date;  // 입력된 날짜
//    private String input_time;  // 입력된 시간
//    public static String selectedLine; // 선택된 호선
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        editTextStationName = findViewById(R.id.editTextStationName);
//        datePicker = findViewById(R.id.datePicker);
//        final TimePicker timePicker = findViewById(R.id.timePicker);
//        final TextView resultTextView = findViewById(R.id.resultTextView);
//        Button predictButton = findViewById(R.id.predictButton);
//
//        // 호선 선택 스피너 초기화
//        Spinner lineSpinner = findViewById(R.id.lineSpinner);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.line_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        lineSpinner.setAdapter(adapter);
//
//        // 호선 선택 리스너 설정
//        lineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                selectedLine = parentView.getItemAtPosition(position).toString();
//
//                // 선택된 호선에 따라 적절한 모델 파일로드
//                loadTFLiteModel(selectedLine); // 선택된 호선을 전달
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // 아무것도 선택되지 않았을 때 기본값으로 첫 번째 항목을 선택
//                selectedLine = parentView.getItemAtPosition(0).toString();
//                // 선택된 호선에 따라 적절한 모델 파일 로드
//                loadTFLiteModel(selectedLine); // 선택된 호선을 전달
//            }
//        });
//
//        predictButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String stationName = getStationName();
//
//                // 역 이름이 유효한지 확인
//                if (!isValidStation(selectedLine, stationName)) {
//                    resultTextView.setText("유효하지 않은 역 이름입니다.");
//                    return;
//                }
//
//                int year = datePicker.getYear();
//                int month = datePicker.getMonth() + 1;
//                int day = datePicker.getDayOfMonth();
//                int hour = timePicker.getHour();
//                int minute = timePicker.getMinute();
//
//                // CSV 파일에서 역에 대한 데이터 읽기
//                float[] stationData = StationFileLoader.readStationData(MainActivity.this, stationName);
//
//                // 입력된 날짜 및 시간 저장
//                input_date = String.format("%04d-%02d-%02d", year, month, day);
//                input_time = String.format("%02d:%02d", hour, minute);
//
//                int dateDiff = calculateDateDiff();
//                int mappedTime = mapTimeToNumber(input_time);
//
//                // 예측 데이터 전처리 및 스케일링
//                float[] inputData = preprocessAndScale(stationData);
//
//                List<Float> predictions = new ArrayList<>();
//                for (int i = 0; i < dateDiff + mappedTime; i++) {
//                    if (i >= lookBack) {
//                        float prediction = tfliteModel.predict(inputData);
//                        predictions.add(prediction);
//
//                        // 입력 데이터 업데이트
//                        System.arraycopy(inputData, 1, inputData, 0, inputData.length - 1);
//                        inputData[inputData.length - 1] = prediction;
//                    }
//                }
//
//                // 예측값 스케일링 역변환
//                float[] scaledPredictions = new float[predictions.size()];
//                for (int i = 0; i < scaledPredictions.length; i++) {
//                    scaledPredictions[i] = predictions.get(i);
//                }
//
//                // CSV 파일에서 최소값과 최대값을 사용하여 스케일링 및 역변환
//                float[] originalPredictions = preprocessAndInverseScale(scaledPredictions, stationData);
//
//                // 역변환된 예측값 사용
//                float lastOriginalPrediction = originalPredictions[originalPredictions.length - 1];
//
//                String result = String.format("날짜 차: %d\n날짜: %s\n입력 시간: %s\n혼잡도: %.2f",
//                        dateDiff, input_date, input_time, (lastOriginalPrediction/26541)*250);
//                resultTextView.setText(result);
//            }
//        });
//    }
//
//    // 선택된 호선에 해당하는 TFLite 모델 파일 로드
//    private void loadTFLiteModel(String selectedLine) {
//        // 선택된 호선에 따라 적절한 모델 파일 경로 가져오기
//        String modelFilePath = getTFLiteModelPath(selectedLine);
//
//        // 모델 파일 경로를 사용하여 모델 로드
//        Interpreter.Options options = new Interpreter.Options()
//                .addDelegate(new FlexDelegate())
//                .setUseXNNPACK(false);
//        tfliteModel = new TFLiteModel(this, modelFilePath, options);
//    }
//
//    // 선택된 호선에 따라 적절한 TFLite 모델 파일 경로 반환
//    private String getTFLiteModelPath(String selectedLine) {
//        return selectedLine + "/model.tflite"; // 모델 파일의 경로 반환
//    }
//
//    // 날짜 차 구하는 메서드
//    private int calculateDateDiff() {
//        try {
//            // 년, 월, 일 입력 받기(일단 임의의 값으로 설정)
//            int year = datePicker.getYear();
//            int month = datePicker.getMonth() + 1;
//            int day = datePicker.getDayOfMonth();
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//            // inputDate를 datePicker에서 선택한 날짜로 설정
//            Date inputDate = sdf.parse(String.format("%04d-%02d-%02d", year, month, day));
//
//            // staticDate는 고정
//            Date staticDate = sdf.parse("2023-10-31");
//
//            // 입력 날짜와 마지막 날짜 간격 계산
//            long dateDiff = Math.max((inputDate.getTime() - staticDate.getTime()) / (1000 * 60 * 60 * 24), 0);
//            return (int) dateDiff;
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return 0;  // 예외 처리 - 날짜 파싱 오류 시 기본값 반환
//        }
//    }
//
//    private int mapTimeToNumber(String timeStr) {
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        try {
//            Date timeObj = sdf.parse(timeStr);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(timeObj);
//
//            int hour = calendar.get(Calendar.HOUR_OF_DAY);
//
//            // 시간대에 따라 숫자로 매핑
//            if (4 <= hour && hour < 6) {
//                return 1;
//            } else if (6 <= hour && hour < 7) {
//                return 2;
//            } else if (7 <= hour && hour < 8) {
//                return 3;
//            } else if (8 <= hour && hour < 9) {
//                return 4;
//            } else if (9 <= hour && hour < 10) {
//                return 5;
//            } else if (10 <= hour && hour < 11) {
//                return 6;
//            } else if (11 <= hour && hour < 12) {
//                return 7;
//            } else if (12 <= hour && hour < 13) {
//                return 8;
//            } else if (13 <= hour && hour < 14) {
//                return 9;
//            } else if (14 <= hour && hour < 15) {
//                return 10;
//            } else if (15 <= hour && hour < 16) {
//                return 11;
//            } else if (16 <= hour && hour < 17) {
//                return 12;
//            } else if (17 <= hour && hour < 18) {
//                return 13;
//            } else if (18 <= hour && hour < 19) {
//                return 14;
//            } else if (19 <= hour && hour < 20) {
//                return 15;
//            } else if (20 <= hour && hour < 21) {
//                return 16;
//            } else if (21 <= hour && hour < 22) {
//                return 17;
//            } else if (22 <= hour && hour < 23) {
//                return 18;
//            } else if (hour >= 23 || hour < 2) {
//                return 19;
//            } else {
//                return 0;  // 예외 처리 - 해당되지 않는 경우
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return 0;  // 파싱 오류 발생 시 기본값 반환
//        }
//    }
//
//    private String getStationName() {
//        return editTextStationName.getText().toString();
//    }
//
//    // 예측 데이터 전처리 및 스케일링 함수 추가
//    private float[] preprocessAndScale(float[] stationData) {
//        // 예측에 사용될 입력 데이터 준비
//        float[] inputData = new float[stationData.length];
//
//        // 읽어온 데이터의 최소값과 최대값을 사용하여 스케일링
//        float min = Float.MAX_VALUE;
//        float max = Float.MIN_VALUE;
//        for (float data : stationData) {
//            if (data < min) {
//                min = data;
//            }
//            if (data > max) {
//                max = data;
//            }
//        }
//
//        for (int i = 0; i < inputData.length; i++) {
//            inputData[i] = (stationData[i] - min) / (max - min); // 스케일링
//        }
//
//        return inputData;
//    }
//
//    // 스케일링된 예측값을 원래 데이터 스케일로 역변환
//    private float[] preprocessAndInverseScale(float[] scaledPredictions, float[] originalData) {
//        float min = Float.MAX_VALUE;
//        float max = Float.MIN_VALUE;
//        for (float data : originalData) {
//            if (data < min) {
//                min = data;
//            }
//            if (data > max) {
//                max = data;
//            }
//        }
//
//        float[] originalPredictions = new float[scaledPredictions.length];
//        for (int i = 0; i < scaledPredictions.length; i++) {
//            originalPredictions[i] = scaledPredictions[i] * (max - min) + min; // 역변환
//        }
//
//        return originalPredictions;
//    }
//
//    // 역 이름이 유효한지를 확인하는 메서드
//    private boolean isValidStation(String selectedLine, String stationName) {
//        // 역 이름이 비어 있지 않고, 해당 역의 데이터 파일이 있는지 확인
//        return !stationName.isEmpty() && hasStationDataFile(selectedLine, stationName);
//    }
//
//    // 해당 역의 데이터 파일이 있는지 확인하는 메서드
//    private boolean hasStationDataFile(String selectedLine, String stationName) {
//        try {
//            // 역 이름에 해당하는 CSV 파일 경로
//            String csvFilePath = selectedLine + "/" + stationName + ".csv";
//
//            // asset 폴더에서 CSV 파일을 읽어오기 위해 AssetManager 사용
//            AssetManager assetManager = getAssets();
//            assetManager.open(csvFilePath).close(); // 파일을 열어서 닫기만 함
//            return true;
//        } catch (IOException e) {
//            // 파일이 존재하지 않는 경우
//            return false;
//        }
//    }
//}
