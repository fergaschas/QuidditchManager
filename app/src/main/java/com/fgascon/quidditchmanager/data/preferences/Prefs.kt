package com.fgascon.quidditchmanager.data.preferences

import android.content.Context

class Prefs(val context: Context) {

    val SHARED_PREFERENCES_NAME = "shared_preferences_name"
    val SHARED_LOGGED = "shared_logged"

    val storage = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setLogged(logged: Boolean){
        storage.edit().putBoolean(SHARED_LOGGED, logged).apply()
    }

    fun getLogged():Boolean{
        return storage.getBoolean(SHARED_LOGGED, false)
    }
}