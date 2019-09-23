package com.pschsch.vkgarbagecleaner.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import kotlin.reflect.KClass

class SharedPreferenceManager(c: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(c)

    fun clearToken(){
        prefs.edit().remove(TOKEN).apply()
    }

    fun <T> saveData(key: String, value: T) {
        val editor = prefs.edit()
        when (value) {
            is String -> {
                editor.putString(key, value)
            }
            is Int -> {
                editor.putInt(key, value)
            }
            else -> {
                Log.w(TAG, "Unexpected value type, can not save this")
            }
        }
        editor.apply()
    }

    fun getString(key : String) : String {
        return prefs.getString(key, "").orEmpty()
    }

    fun getInt(key : String) : Int {
        return prefs.getInt(key, 0).orReturn(0)
    }

    companion object {
        const val TAG = "SharedPreferenceManager"
        const val TOKEN = "vkAuthToken"
        const val USER_ID = "vkUserId"
    }

}