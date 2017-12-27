package com.veetaw.nospy.helper

import android.content.Context
import com.veetaw.nospy.Constants


class PreferencesHelper(context: Context) {
    private val c = Constants()

    private val sharedPrefs = context.getSharedPreferences(
            c.sharedPrefFileName, Context.MODE_PRIVATE)

    fun prefsExist(): Boolean {
        return sharedPrefs.contains(c.audioPrefName) && sharedPrefs.contains(c.cameraPrefName)
    }

    fun setBool(entry: String, value: Boolean) {
        sharedPrefs.edit().putBoolean(entry, value).apply()
    }

    fun getBool(entry: String, default: Boolean): Boolean {
        return sharedPrefs.getBoolean(entry, default)
    }

}