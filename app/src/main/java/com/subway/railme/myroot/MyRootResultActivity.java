package com.subway.railme.myroot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.subway.railme.R;

public class MyRootResultActivity extends AppCompatActivity {

    private TextView tvdStation;
    private TextView tvdStation2;
    private TextView tvaStation;
    private TextView tvaStation2;

    private TextView tvrouteResult;
    private ImageView ibgoback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_root_result);

        Intent intent = getIntent();
        String departureStation = intent.getStringExtra("departureStation");
        String destinationStation = intent.getStringExtra("destinationStation");
        String departureTime = intent.getStringExtra("departureTime");

        tvdStation = findViewById(R.id.tv_dStation);
        tvdStation2 = findViewById(R.id.tv_dStation2);
        tvaStation = findViewById(R.id.tv_aStation);
        tvaStation2 = findViewById(R.id.tv_aStation2);
        tvrouteResult = findViewById(R.id.tv_routeResult);
        ibgoback = findViewById(R.id.IB_goback);

        tvdStation.setText(departureStation + "역");
        tvdStation2.setText(departureStation + "역");
        tvaStation.setText(destinationStation + "역");
        tvaStation2.setText(destinationStation + "역");
        tvrouteResult.setText("소요시간: " + departureTime + "분");

        ibgoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
