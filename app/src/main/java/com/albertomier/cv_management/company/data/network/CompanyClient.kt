package com.albertomier.cv_management.company.data.network

import com.albertomier.cv_management.company.data.network.response.AddCompanyResponse
import com.albertomier.cv_management.company.data.network.response.CompanyResponse
import com.albertomier.cv_management.company.data.network.response.CompanySingleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CompanyClient {
    @GET("auth/company")
    suspend fun getCompanyList(
        @Header("Authorization") authHeader: String
    ): Response<CompanyResponse>

    @GET("auth/company/{id}")
    suspend fun getCompanyById(
        @Header("Authorization") authHeader: String,
        @Path("id") id: Int,
    ): Response<CompanySingleResponse>

    @POST("auth/company")
    suspend fun addCompany(
        @Header("Authorization") authHeader: String,
        @Body addCompanyData: AddCompanyData
    ): Response<AddCompanyResponse>
}