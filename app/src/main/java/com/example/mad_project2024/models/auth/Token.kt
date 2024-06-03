package com.example.mad_project2024.models.auth

import android.content.Context
import android.preference.PreferenceManager

data class Token(
    val access_token: String,
    val token_type: String
)

object TokenManager {
    private const val ACCESS_TOKEN_KEY = "access_token"
    private const val TOKEN_TYPE_KEY = "token_type"

    fun saveToken(accessToken: String, tokenType: String, context: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString(ACCESS_TOKEN_KEY, accessToken)
        editor.putString(TOKEN_TYPE_KEY, tokenType)
        editor.apply()
    }

    fun getToken(context: Context): String? {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val accessToken = sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
        val tokenType = sharedPreferences.getString(TOKEN_TYPE_KEY, null)
        return if (accessToken != null && tokenType != null) {
            "$tokenType $accessToken"
        } else {
            null
        }
    }

    fun clearToken(context: Context) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.remove(ACCESS_TOKEN_KEY)
        editor.remove(TOKEN_TYPE_KEY)
        editor.apply()
    }
}
