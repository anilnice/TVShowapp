package com.anil.tvshowapp.util

import android.content.Context
import android.content.SharedPreferences
import com.anil.tvshowapp.domain.Show
import com.google.gson.Gson


class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveTvShowData(value: Show) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        editor.putString(Constants.SHARED_TV_SHOW, json)
        editor.apply()
    }

    fun getTvShowData(): Show {
        val moviestring = sharedPreferences.getString(Constants.SHARED_TV_SHOW, null)
        return Gson().fromJson(moviestring, Show::class.java)
    }
}