package com.albertomier.cv_management.profile.data.network

import com.albertomier.cv_management.profile.data.network.request.AddEducationData
import com.albertomier.cv_management.profile.data.network.request.AddExperienceData
import com.albertomier.cv_management.profile.data.network.request.AddPersonalData
import com.albertomier.cv_management.profile.data.network.response.AddEducationResponse
import com.albertomier.cv_management.profile.data.network.response.AddExperienceResponse
import com.albertomier.cv_management.profile.data.network.response.GetEducationDataResponse
import com.albertomier.cv_management.profile.data.network.response.GetExperienceDataResponse
import com.albertomier.cv_management.profile.data.network.response.GetPersonalDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ProfileClient {

    /**
     * GET DATA
     */
    @GET("auth/get-personal-data")
    suspend fun getPersonalData(@Header("Authorization") authHeader: String): Response<GetPersonalDataResponse>

    @GET("auth/get-experience-data")
    suspend fun getExperienceData(@Header("Authorization") authHeader: String): Response<GetExperienceDataResponse>

    @GET("auth/get-education-data")
    suspend fun getEducationData(@Header("Authorization") authHeader: String): Response<GetEducationDataResponse>

    /**
     * ADD DATA
     */
    @POST("auth/add-personal-data")
    suspend fun addPersonalData(
        @Header("Authorization") authHeader: String,
        @Body addPersonalData: AddPersonalData
    ): Response<AddExperienceResponse>

    @POST("auth/add-experience-data")
    suspend fun addExperienceData(
        @Header("Authorization") authHeader: String,
        @Body addExperienceData: AddExperienceData
    ): Response<AddExperienceResponse>

    @POST("auth/add-education-data")
    suspend fun addEducationData(
        @Header("Authorization") authHeader: String,
        @Body addEducationData: AddEducationData
    ): Response<AddEducationResponse>

    /**
     * UPDATE DATA
     */
    @POST("auth/update-personal-data")
    suspend fun updatePersonalData(
        @Header("Authorization") authHeader: String,
        @Body addPersonalData: AddPersonalData
    ): Response<AddExperienceResponse>

    @POST("auth/update-experience-data")
    suspend fun updateExperienceData(
        @Header("Authorization") authHeader: String,
        @Body addExperienceData: AddExperienceData
    ): Response<AddExperienceResponse>

    @POST("auth/update-education-data")
    suspend fun updateEducationData(
        @Header("Authorization") authHeader: String,
        @Body addEducationData: AddEducationData
    ): Response<AddEducationResponse>
}