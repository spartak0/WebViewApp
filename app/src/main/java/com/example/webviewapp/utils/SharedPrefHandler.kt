package com.example.webviewapp.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefHandler(context: Context) {
    private val settings: SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return settings.getString(key, null)
    }

}