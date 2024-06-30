package com.example.mad_project2024.repository

import com.example.mad_project2024.api.ApiService
import com.example.mad_project2024.models.Suggestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SuggestionsRepository @Inject constructor(private val api: ApiService) {

    suspend fun getSuggestions(): Result<List<Suggestion>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getSuggestions().execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to fetch suggestions: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun submitSuggestion(
        country: String,
        subCategory: String,
        content: String
    ): Result<Void> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.submitSuggestion(country, subCategory, content).execute()
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception("Failed to submit suggestion: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            } as Result<Void>
        }
    }

    suspend fun approveSuggestion(id: String): Result<Void> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.approveSuggestion(id).execute()
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception("Failed to approve suggestion: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            } as Result<Void>
        }
    }

    suspend fun declineSuggestion(id: String): Result<Void> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.declineSuggestion(id).execute()
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception("Failed to decline suggestion: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            } as Result<Void>
        }
    }

    suspend fun deleteSuggestion(id: String): Result<Void> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.deleteSuggestion(id).execute()
                if (response.isSuccessful) {
                    Result.success(response.body())
                } else {
                    Result.failure(Exception("Failed to delete suggestion: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            } as Result<Void>
        }
    }
}
