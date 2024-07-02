package com.example.mad_project2024.models

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.Gson

data class Token(
    val access_token: String,
    val token_type: String,
    val refresh_token: String
)

@Singleton
class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        //private const val ACCESS_TOKEN = "access_token"
        //private const val REFRESH_TOKEN = "refresh_token"
        //private const val TOKEN_TYPE = "token_type"
        private const val TOKEN_KEY = "token"
    }

    fun saveToken(token: Token) {
        val tokenString = gson.toJson(token)
        prefs.edit().putString(TOKEN_KEY, tokenString).apply()
            /*.apply {
            putString(ACCESS_TOKEN, token.access_token)
            putString(REFRESH_TOKEN, token.refresh_token)
            putString(TOKEN_TYPE, token.token_type)
            apply()*/
        }


    fun getToken(): Token? {
        val tokenString = prefs.getString(TOKEN_KEY, null)
        return tokenString?.let{
            gson.fromJson(it, Token::class.java)
        }
    }
        /*
        val accessToken = prefs.getString(ACCESS_TOKEN, null)
        val refreshToken = prefs.getString(REFRESH_TOKEN, null)
        val tokenType = prefs.getString(TOKEN_TYPE, null)
        return if (accessToken != null && refreshToken != null && tokenType != null) {
            Token(access_token = accessToken, token_type = tokenType, refresh_token = refreshToken)
        } else {
            null
        }
        }*/


    fun clearToken() {
        prefs.edit().remove(TOKEN_KEY).apply()
    }

}

        /*.apply {
            remove(ACCESS_TOKEN)
            remove(REFRESH_TOKEN)
            remove(TOKEN_TYPE)
            apply()
        }
    }
}*/
