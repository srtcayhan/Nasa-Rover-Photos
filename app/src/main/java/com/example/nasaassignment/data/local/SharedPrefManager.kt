package com.example.nasaassignment.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("sharedPreferencesUtil", Context.MODE_PRIVATE)

    fun setOnboardingSeen(){
        sharedPreferences.edit().putBoolean("ONBOARDING",true).apply()
    }
    
    fun getOnboardingSeen(): Boolean {
        return sharedPreferences.getBoolean("ONBOARDING", false)
    }
}