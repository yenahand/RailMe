package com.subway.railme.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesManager {
    private static final String PREFERENCES_NAME = "my_preferences";

    public static SharedPreferences getPreferences(Context mContext){
        return mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static void clearPreferences(Context context){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static void setLoginInfo(OnCompleteListener<AuthResult> context, String Email, String Password){
        SharedPreferences prefs = getPreferences((Context) context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Email", Email);
        editor.putString("Password", Password);

        editor.apply();
    }

    public static Map<String, String> getLoginInfo(OnCompleteListener<AuthResult> context){
        SharedPreferences prefs = getPreferences((Context) context);
        Map<String, String> LoginInfo = new HashMap<>();
        String Email = prefs.getString("Email", "");
        String Password = prefs.getString("Password", "");

        LoginInfo.put("Email", Email);
        LoginInfo.put("Password", Password);

        return LoginInfo;
    }
}
