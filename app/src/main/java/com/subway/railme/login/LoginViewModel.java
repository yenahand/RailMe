package com.subway.railme.login;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
//
//public class LoginViewModel extends ViewModel {
//    // mvvm 패턴에서 view model 부분
//    // livedata 넣는 곳...
//    // mutablelivedata(repository)와 livedata(loginviewmodel)가 연결
//
//    private MutableLiveData<String> Login;
//    private LoginRepository loginRepository = new LoginRepository();
//
//    /*public LoginViewModel(@NonNull Application application) {
//        super(application);
//        Login = loginRepository.findAll();
//    }*/
//
//    public MutableLiveData<String> getLogin() {
//        if(Login == null) {
//            Login = new MutableLiveData<String>();
//        }
//        return Login;
//    }
//}
