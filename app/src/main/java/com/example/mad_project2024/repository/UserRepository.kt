package com.example.mad_project2024.repository

import com.example.mad_project2024.api.RetrofitClient
import com.example.mad_project2024.models.MessageResponse
import com.example.mad_project2024.models.user.ListUser
import com.example.mad_project2024.models.user.SelfUpdateUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    private val api = RetrofitClient.api

    suspend fun getSelfUser(token: String): Result<ListUser> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getSelfUser("Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to fetch self user: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getAllUsers(token: String): Result<List<ListUser>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllUsers("Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to fetch all users: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getUser(nickName: String, token: String): Result<ListUser> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getUser(nickName, "Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to fetch user: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun selfUpdateUser(updateData: SelfUpdateUser, token: String): Result<MessageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.selfUpdateUser(updateData, "Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to update self user: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun upgradeUser(nickName: String, token: String): Result<MessageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.upgradeUser(nickName, "Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to upgrade user: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun downgradeUser(nickName: String, token: String): Result<MessageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.downgradeUser(nickName, "Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to downgrade user: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun selfDeleteUser(token: String): Result<MessageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.selfDeleteUser("Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to delete self user: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun deleteUser(nickName: String, token: String): Result<MessageResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.deleteUser(nickName, "Bearer $token").execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to delete user: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}