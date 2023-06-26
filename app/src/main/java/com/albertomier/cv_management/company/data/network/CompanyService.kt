package com.albertomier.cv_management.company.data.network

import com.albertomier.cv_management.company.data.network.request.AddCompanyData
import com.albertomier.cv_management.company.data.network.request.AddInterviewData
import com.albertomier.cv_management.company.data.network.response.AddCompanyResponse
import com.albertomier.cv_management.company.data.network.response.AddInterviewResponse
import com.albertomier.cv_management.company.data.network.response.CompanyItemResponse
import com.albertomier.cv_management.company.data.network.response.CompanyResponse
import com.albertomier.cv_management.company.data.network.response.CompanySingleResponse
import com.albertomier.cv_management.company.data.network.response.InterviewItemResponse
import com.albertomier.cv_management.company.data.network.response.InterviewResponse
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
        description: String
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
                    contactEmail = contactEmail,
                    description = description
                )
            )
            response.body()!!.data
        }
    }

    suspend fun getInterviewListByCompanyId(companyId: Int): List<InterviewItemResponse> {
        return withContext(Dispatchers.IO) {
            val response: Response<InterviewResponse> =
                companyClient.getInterviewListByCompanyId(Utils.getAuthorization(), companyId)

            if (response.isSuccessful) {
                response.body()!!.data
            } else {
                emptyList()
            }
        }
    }

    suspend fun addInterview(
        companyId: Int,
        date: String,
        hour: String,
        comment: String,
        done: Int
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddInterviewResponse> = companyClient.addInterview(
                Utils.getAuthorization(),
                AddInterviewData(
                    companyId = companyId,
                    date = date,
                    hour = hour,
                    comment = comment,
                    done = done
                )
            )
            response.body()!!.data
        }
    }
}