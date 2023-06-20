package com.albertomier.cv_management.company.data

import com.albertomier.cv_management.company.data.network.CompanyService
import com.albertomier.cv_management.company.domain.model.CompanyItem
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

    suspend fun addCompany(
        name: String,
        phone: String,
        email: String,
        contactName: String,
        contactPhone: String,
        contactEmail: String,
    ): ApiResponseStatus<String> =
        makeNetworkCall {
            val result = companyService.addCompany(
                name = name,
                phone = phone,
                email = email,
                contactName = contactName,
                contactPhone = contactPhone,
                contactEmail = contactEmail
            )
            ""
        }
}