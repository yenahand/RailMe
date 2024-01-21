package com.subway.railme.login;

import static com.android.volley.VolleyLog.d;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> Login;

    public MutableLiveData<String> getLogin() {
        if(Login == null) {
            Login = new MutableLiveData<String>();
        }
        return Login;
    }
}
