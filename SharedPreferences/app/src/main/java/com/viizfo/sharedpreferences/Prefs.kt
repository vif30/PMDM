package com.viizfo.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    val prefs: SharedPreferences =context.getSharedPreferences (PREFS_NAME, Context.MODE_PRIVATE)
    var name: String
        get ()=prefs.getString(SHARED_NAME, "") ?: ""
        set (value)=prefs.edit().putString (SHARED_NAME, value).apply ()

    companion object {
        const val PREFS_NAME="com.viizfo.sharedpreferencesexample"
        const val SHARED_NAME="shared_name"
    }
}