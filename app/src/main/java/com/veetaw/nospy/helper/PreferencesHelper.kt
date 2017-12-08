package com.veetaw.nospy.helper

import android.content.Context


class PreferencesHelper(context: Context) {
    private val sharedPrefs = context.getSharedPreferences(
            "com.veetaw.nospy.nospy_shared_prefs", Context.MODE_PRIVATE)

    fun prefsExist(): Boolean {
        return sharedPrefs.contains("audioListenerEnabled")
    }

    fun setBool(entry: String, value: Boolean) {
        sharedPrefs.edit().putBoolean(entry, value).apply()
    }

    fun getBool(entry: String, default: Boolean): Boolean {
        return sharedPrefs.getBoolean(entry, default)
    }

}