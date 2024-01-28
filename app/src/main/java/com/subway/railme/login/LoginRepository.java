package com.subway.railme.login;

import androidx.lifecycle.MutableLiveData;

import java.util.Objects;

public class LoginRepository {

    private _LoginNotice = MutableLiveData<String>();

    // mvvm 패턴에서 model 부분임
    private String email;
    private String password;

    public LoginRepository() {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public boolean equals(Objects obj){
        LoginRepository other = (JoinRequest)obj;
        if (other == null) return false;

        boolean result1 = email != null && email.length() > 0;
        result1 = result1 && email.equals(other.email);

        boolean result2 = password != null && password.length() > 0;
        result2 = result2 && password.equals(other.password);

        return result1;
        return result2;
    }
}