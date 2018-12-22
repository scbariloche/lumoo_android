package de.thinkad.lumoo.business.services

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences


class MySharedPreferences(context: Context) {
    private val PREF_NAME = "de.thinkad.lumoo.welcome"
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor? = null

    // shared pref mode
    var PRIVATE_MODE = 0

    // Shared preferences file name
    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"

    init {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor?.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor?.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }
}

