package com.example.mad_project2024.api

import com.example.mad_project2024.models.MessageResponse
import com.example.mad_project2024.models.auth.OAuth2PasswordRequest
import com.example.mad_project2024.models.auth.Token
import com.example.mad_project2024.models.user.CreateUser
import com.example.mad_project2024.models.user.ListUser
import com.example.mad_project2024.models.user.SelfUpdateUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    @Headers("Content-Type: application/json")
    fun login(@Body form: OAuth2PasswordRequest): Call<Token>

    @POST("auth/register")
    @Headers("Content-Type: application/json")
    fun register(@Body createData: CreateUser): Call<Void>

    @POST("auth/login-guest")
    fun guestAccess(): Call<Token>

    @GET("private/usr/self")
    fun getSelfUser(@Header("Authorization") token: String): Call<ListUser>

    @GET("private/usr/all")
    fun getAllUsers(@Header("Authorization") token: String): Call<List<ListUser>>

    @GET("private/usr/{nickName}")
    fun getUser(@Path("nickName") nickName: String, @Header("Authorization") token: String): Call<ListUser>

    @PUT("/private/usr/self-update")
    fun selfUpdateUser(@Body updateData: SelfUpdateUser, @Header("Authorization") token: String): Call<MessageResponse>

    @PUT("private/usr/upgrade-to-admin")
    fun upgradeUser(@Query("nickName") nickName: String, @Header("Authorization") token: String): Call<MessageResponse>

    @PUT("/private/usr/downgrade-to-regular")
    fun downgradeUser(@Query("nickName") nickName: String, @Header("Authorization") token: String): Call<MessageResponse>

    @DELETE("private/usr/self-delete")
    fun selfDeleteUser(@Header("Authorization") token: String): Call<MessageResponse>

    @DELETE("/private/usr/{nickName}")
    fun deleteUser(@Path("nickName") nickName: String, @Header("Authorization") token: String): Call<MessageResponse>
}