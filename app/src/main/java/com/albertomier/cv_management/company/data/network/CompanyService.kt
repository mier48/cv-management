package com.albertomier.cv_management.company.data.network

import com.albertomier.cv_management.company.data.network.response.AddCompanyResponse
import com.albertomier.cv_management.company.data.network.response.CompanyItemResponse
import com.albertomier.cv_management.company.data.network.response.CompanyResponse
import com.albertomier.cv_management.company.data.network.response.CompanySingleResponse
import com.albertomier.cv_management.company.data.network.response.SuccessResponse
import com.albertomier.cv_management.core.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CompanyService @Inject constructor(private val companyClient: CompanyClient) {

    suspend fun getCompanyList(): List<CompanyItemResponse> {
        return withContext(Dispatchers.IO) {
            val response: Response<CompanyResponse> =
                companyClient.getCompanyList(Utils.getAuthorization())

            if (response.isSuccessful) {
                response.body()!!.data
            } else {
                emptyList()
            }
        }
    }

    suspend fun getCompanyById(id: Int): CompanyItemResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<CompanySingleResponse> =
                companyClient.getCompanyById(Utils.getAuthorization(), id)
            response.body()!!.data
        }
    }

    suspend fun addCompany(
        name: String,
        phone: String,
        email: String,
        contactName: String,
        contactPhone: String,
        contactEmail: String,
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddCompanyResponse> = companyClient.addCompany(
                Utils.getAuthorization(),
                AddCompanyData(
                    name = name,
                    phone = phone,
                    email = email,
                    contactName = contactName,
                    contactPhone = contactPhone,
                    contactEmail = contactEmail
                )
            )
            response.body()!!.data
        }
    }
}