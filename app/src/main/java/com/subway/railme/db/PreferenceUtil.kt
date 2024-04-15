package com.subway.railme.db

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("inputStationInfo",0) //파일 이름 및 모드

    fun getStationInfo(key :String, defValue:String):String{
        return prefs.getString(key,defValue).toString()
    }
    fun setStationInfo(key:String,str:String){
        prefs.edit().putString(key,str).apply()
    }

    fun setTime(key:String,sh:String){
        prefs.edit().putString(key,sh).apply()
    }

    fun getTime(key:String, no:String):String{
        return prefs.getString(key,no).toString()
    }


    fun getDate(key: String,date: String):String{
        return prefs.getString(key,date).toString()
    }

    fun setDate(key: String,date:String){
        prefs.edit().putString(key,date).apply()
    }

}