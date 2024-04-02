package com.subway.railme.myroot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.subway.railme.R;
import com.subway.railme.databinding.ActivityLoginBinding;
import com.subway.railme.databinding.ActivityMyRootResultBinding;

public class MyRootResultActivity extends AppCompatActivity {

    private TextView tvmyresultstart;
    private TextView tvmyresultarrive;
    //private TextView tvmyresulttime;
    private ImageButton ibGoBack;

    @SuppressLint("MissingInflatedId")
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
        //tvmyresulttime = findViewById(R.id.tv_myresult_time);
        //ibGoBack = findViewById(R.id.IB_GoBack);

        tvmyresultstart.setText("출발역: " + departureStation);
        tvmyresultarrive.setText("도착역: " + destinationStation);
        //tvmyresulttime.setText("" + departureTime + "분");

        /*ibGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.myRootFragment, new MyRootFragment())
                        .commit();
            }
        });*/

    }
}