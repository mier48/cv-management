package com.albertomier.cv_management.profile.data.network

import com.albertomier.cv_management.profile.data.network.request.AddExperienceData
import com.albertomier.cv_management.profile.data.network.request.AddPersonalData
import com.albertomier.cv_management.profile.data.network.response.AddExperienceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ProfileClient {

    @POST("auth/add-experience")
    suspend fun addExperience(
        @Header("Authorization") authHeader: String,
        @Body addExperienceData: AddExperienceData
    ): Response<AddExperienceResponse>

    @POST("auth/add-personal-data")
    suspend fun addPersonalData(
        @Header("Authorization") authHeader: String,
        @Body addPersonalData: AddPersonalData
    ): Response<AddExperienceResponse>
}