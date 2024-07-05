package com.example.mad_project2024.repository

import com.example.mad_project2024.api.ApiService
import com.example.mad_project2024.models.Country
import com.example.mad_project2024.models.Token
import com.example.mad_project2024.models.user.CreateUser
import com.example.mad_project2024.models.user.ListUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: ApiService) {

    private val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+$).{8,}$"

    fun isPasswordValid(password: String): Boolean {
        return password.matches(passwordPattern.toRegex())
    }

    // Retrieve password requirements
    fun getPasswordRequirements(): List<String> {
        return listOf(
            "Password must be at least 8 characters long",
            "Password must contain at least one uppercase letter",
            "Password must contain at least one lowercase letter",
            "Password must contain at least one number",
            "Password must contain at least one special character"
        )
    }
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

    suspend fun register(nickName: String, email: String, displayedName: String, password: String, countryCode: String): Result<Any> {
        return withContext(Dispatchers.IO) {
            try {
                val createUser = CreateUser(nickName, email, displayedName, password, countryCode)
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

    suspend fun getUserDetails(token: String): Result<ListUser> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getSelfUser("Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to fetch user details: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    suspend fun getCountries(): Result<List<Country>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getCountries().execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to fetch countries: ${response.message()}"))
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
