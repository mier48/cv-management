package com.albertomier.cv_management.company.data

import androidx.lifecycle.LiveData
import com.albertomier.cv_management.company.data.network.CompanyClient
import com.albertomier.cv_management.company.data.network.CompanyService
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.company.domain.model.InterviewItem
import com.albertomier.cv_management.company.domain.model.toDomain
import com.albertomier.cv_management.core.api.ApiResponse
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.network.AppExecutors
import com.albertomier.cv_management.core.repository.NetworkBoundResource
import com.albertomier.cv_management.core.repository.Resource
import com.albertomier.cv_management.core.network.makeNetworkCall
import com.albertomier.cv_management.core.utils.Utils
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val service: CompanyService,
    private val api: CompanyClient
) {

    fun loadCompanies(): LiveData<Resource<List<CompanyItem>>> {
        return object : NetworkBoundResource<List<CompanyItem>, List<CompanyItem>>(appExecutors) {
            override fun saveCallResult(item: List<CompanyItem>) {
                //dao.insertAll(item)
            }

            override fun loadFromDB(): LiveData<List<CompanyItem>> {
                 //return dao.getCompanies()
            }

            override fun createCall(): LiveData<ApiResponse<List<CompanyItem>>> {
                return api.getCompanies(Utils.getAuthorization())
            }

            override fun shouldFetch(data: List<CompanyItem>?): Boolean {
                return data == null
            }

        }.asLiveData()
    }

    suspend fun getCompanyList(): ApiResponseStatus<List<CompanyItem>> =
        makeNetworkCall {
            val result = service.getCompanyList()
            result.map { it.toDomain() }
        }

    suspend fun getCompanyById(id: Int): ApiResponseStatus<CompanyItem?> =
        makeNetworkCall {
            val result = service.getCompanyById(id)

            result?.toDomain()
        }

    suspend fun getInterviewListByCompanyId(companyId: Int): ApiResponseStatus<List<InterviewItem>> =
        makeNetworkCall {
            val result = service.getInterviewListByCompanyId(companyId)
            result.map { it.toDomain() }
        }

    suspend fun addCompany(
        name: String,
        phone: String,
        email: String,
        contactName: String,
        contactPhone: String,
        contactEmail: String,
        description: String
    ): ApiResponseStatus<String> =
        makeNetworkCall {
            service.addCompany(
                name = name,
                phone = phone,
                email = email,
                contactName = contactName,
                contactPhone = contactPhone,
                contactEmail = contactEmail,
                description = description
            ).response
        }

    suspend fun addInterview(
        companyId: Int,
        date: String,
        hour: String,
        comment: String,
        done: Int
    ): ApiResponseStatus<String> =
        makeNetworkCall {
            service.addInterview(
                companyId = companyId,
                date = date,
                hour = hour,
                comment = comment,
                done = done
            ).response
        }
}