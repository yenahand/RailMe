package com.subway.railme.myroot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private Fragment MyRootFragment;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
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
        ///tvmyresulttime = findViewById(R.id.tv_myresult_time);
        ibgoback = findViewById(R.id.IB_goback);

        tvdStation.setText( departureStation+"역");
        tvaStation2.setText( destinationStation+"역");
        tvdStation.setText( departureStation+"역");
        tvaStation2.setText( destinationStation+"역");
        tvrouteResult.setText("소요시간: " + departureTime + "분");

        ibgoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}