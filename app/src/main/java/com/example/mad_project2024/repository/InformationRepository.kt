package com.example.mad_project2024.repository

import com.example.mad_project2024.api.ApiService
import com.example.mad_project2024.models.InformationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InformationRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getInformation(countryCode: String): Result<InformationResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getInformation(countryCode).execute()
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to fetch information: ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            } as Result<InformationResponse>
        }
    }
}
