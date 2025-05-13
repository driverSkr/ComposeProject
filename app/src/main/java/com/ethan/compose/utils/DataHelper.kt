package com.ethan.compose.utils

import android.content.Context

object DataHelper {

    fun setLanguage(context: Context, language: String) {
        val sharedPreferences = context.getSharedPreferences("sp_language", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("sp_language_data", language)
        editor.apply()
    }

    fun getLanguage(context: Context) : String? {
        val sharedPreferences = context.getSharedPreferences("sp_language", Context.MODE_PRIVATE)
        return sharedPreferences.getString("sp_language_data", null)
    }
}