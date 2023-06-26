package com.albertomier.cv_management.company.data.network

import com.albertomier.cv_management.company.data.network.request.AddCompanyData
import com.albertomier.cv_management.company.data.network.request.AddInterviewData
import com.albertomier.cv_management.company.data.network.response.AddCompanyResponse
import com.albertomier.cv_management.company.data.network.response.AddInterviewResponse
import com.albertomier.cv_management.company.data.network.response.CompanyResponse
import com.albertomier.cv_management.company.data.network.response.CompanySingleResponse
import com.albertomier.cv_management.company.data.network.response.InterviewResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CompanyClient {

    /**
     * GET DATA
     */
    @GET("auth/company")
    suspend fun getCompanyList(
        @Header("Authorization") authHeader: String
    ): Response<CompanyResponse>

    @GET("auth/company/{id}")
    suspend fun getCompanyById(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int,
    ): Response<CompanySingleResponse>

    @GET("auth/interview/{companyId}")
    suspend fun getInterviewListByCompanyId(
        @Header("Authorization") authHeader: String,
        @Path("companyId") companyId: Int,
    ): Response<InterviewResponse>

    /**
     * ADD
     */
    @POST("auth/company")
    suspend fun addCompany(
        @Header("Authorization") authHeader: String,
        @Body addCompanyData: AddCompanyData
    ): Response<AddCompanyResponse>

    @POST("auth/interview")
    suspend fun addInterview(
        @Header("Authorization") authHeader: String,
        @Body addInterviewData: AddInterviewData
    ): Response<AddInterviewResponse>

    /**
     * UPDATE
     */
    @POST("auth/company/{id}")
    suspend fun updateCompany(
        @Header("Authorization") authHeader: String,
        @Body addCompanyData: AddCompanyData,
        @Path("id") id: Int
    ): Response<AddCompanyResponse>

    @POST("auth/interview/{id}")
    suspend fun updateInterview(
        @Header("Authorization") authHeader: String,
        @Body addInterviewData: AddInterviewData,
        @Path("id") id: Int
    ): Response<AddInterviewResponse>

    /**
     * DELETE
     */
    @DELETE("auth/company/{id}")
    suspend fun deleteCompany(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<AddCompanyResponse>

    @DELETE("auth/interview/{id}")
    suspend fun deleteInterview(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int
    ): Response<AddInterviewResponse>
}