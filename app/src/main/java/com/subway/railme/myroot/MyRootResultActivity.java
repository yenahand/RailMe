package com.subway.railme.myroot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityLoginBinding;
import com.subway.railme.databinding.ActivityMyRootResultBinding;

public class MyRootResultActivity extends AppCompatActivity {

    private TextView tvmyresultstart;
    private TextView tvmyresultarrive;
    private TextView tvmyresulttime;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_root_result);

        tvmyresultstart = findViewById(R.id.tv_myresult_start);
        tvmyresultarrive = findViewById(R.id.tv_myresult_arrive);
        tvmyresulttime = findViewById(R.id.tv_myresult_time);

        Intent intent = getIntent();
        if(getIntent().getExtras() != null) {
            String departureStation = intent.getStringExtra("departureStation");
            String destinationStation = intent.getStringExtra("destinationStation");
            String departureTime = intent.getStringExtra("departureTime");

            tvmyresultstart.setText("" + departureStation);
            tvmyresultarrive.setText("" + destinationStation);
            tvmyresulttime.setText("" + departureTime + "분");
        }
    }
}