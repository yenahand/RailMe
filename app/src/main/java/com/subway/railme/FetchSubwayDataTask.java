package com.subway.railme;

import android.os.AsyncTask;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FetchSubwayDataTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        String apiUrl = params[0];
        try {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("subway");
            databaseReference.setValue(apiUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
