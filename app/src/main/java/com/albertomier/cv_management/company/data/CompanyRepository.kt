package com.albertomier.cv_management.company.data

import com.albertomier.cv_management.company.data.network.CompanyService
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.company.domain.model.InterviewItem
import com.albertomier.cv_management.company.domain.model.toDomain
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.network.makeNetworkCall
import javax.inject.Inject

class CompanyRepository @Inject constructor(private val companyService: CompanyService) {

    suspend fun getCompanyList(): ApiResponseStatus<List<CompanyItem>> =
        makeNetworkCall {
            val result = companyService.getCompanyList()
            result.map { it.toDomain() }
        }

    suspend fun getCompanyById(id: Int): ApiResponseStatus<CompanyItem> =
        makeNetworkCall {
            val result = companyService.getCompanyById(id)
            result.toDomain()
        }

    suspend fun getInterviewListByCompanyId(companyId: Int): ApiResponseStatus<List<InterviewItem>> =
        makeNetworkCall {
            val result = companyService.getInterviewListByCompanyId(companyId)
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
            companyService.addCompany(
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
            companyService.addInterview(
                companyId = companyId,
                date = date,
                hour = hour,
                comment = comment,
                done = done
            ).response
        }
}