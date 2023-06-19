package com.albertomier.cv_management.login.data.network

import com.albertomier.cv_management.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginClient {
    //@Headers("Content-Type: application/json")
    @POST("auth/login")
    suspend fun login(@Body loginData: LoginData): Response<LoginResponse>

    @POST("auth/signup")
    suspend fun signup(): Response<LoginResponse>

    @GET("auth/logout")
    suspend fun logout(): Response<LoginResponse>
}