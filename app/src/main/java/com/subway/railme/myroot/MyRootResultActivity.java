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

    private TextView tvmyresultstart;
    private TextView tvmyresultarrive;
    private TextView tvstartup;
    private TextView tvarriveup;
    //private TextView tvmyresulttime;
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
        //String departureTime = intent.getStringExtra("departureTime");

        tvmyresultstart = findViewById(R.id.tv_myresult_start);
        tvmyresultarrive = findViewById(R.id.tv_myresult_arrive);
        tvstartup = findViewById(R.id.tv_start_up);
        tvarriveup = findViewById(R.id.tv_arrive_up);
        //tvmyresulttime = findViewById(R.id.tv_myresult_time);
        ibgoback = findViewById(R.id.IB_goback);

        tvmyresultstart.setText("" + departureStation);
        tvmyresultarrive.setText("" + destinationStation);
        tvstartup.setText("" + departureStation);
        tvarriveup.setText("" + destinationStation);
        //tvmyresulttime.setText("" + departureTime + "분");

        ibgoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}