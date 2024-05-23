package com.data.repository

import com.data.api.RetrofitClient
import com.data.model.auth.OAuth2PasswordRequest
import com.data.model.auth.Token
import com.data.model.user.CreateUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    private val api = RetrofitClient.api

    suspend fun login(username: String, password: String): Result<Token> {
        return withContext(Dispatchers.IO) {
            try {
                val form = OAuth2PasswordRequest(username, password)
                val response = api.login(form).execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to login: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun register(nickName: String, email: String, displayedName: String, password: String): Result<Void> {
        return withContext(Dispatchers.IO) {
            try {
                val createUser = CreateUser(nickName, email, displayedName, password)
                val response = api.register(createUser).execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to register: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun guestAccess(): Result<Token> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.guestAccess().execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to access guest: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}