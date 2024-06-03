package com.example.mad_project2024.repository

import com.example.mad_project2024.api.RetrofitClient
import com.example.mad_project2024.models.auth.Token
import com.example.mad_project2024.models.user.CreateUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    private val api = RetrofitClient.api

    suspend fun login(username: String, password: String): Result<Token> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.login(username = username, password = password).execute()
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

    suspend fun register(nickName: String, email: String, displayedName: String, password: String): Result<Any> {
        return withContext(Dispatchers.IO) {
            try {
                val createUser = CreateUser(nickName, email, displayedName, password)
                val response = api.register(createUser).execute()
                if (response.isSuccessful) {
                    val body = response.body()
                    Result.success(body ?: "Response body was null")
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