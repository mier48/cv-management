package com.albertomier.cv_management.company.domain

import com.albertomier.cv_management.company.data.CompanyRepository
import com.albertomier.cv_management.core.network.ApiResponseStatus
import javax.inject.Inject

class AddCompanyUseCase @Inject constructor(private val repository: CompanyRepository) {

    suspend operator fun invoke(
        name: String,
        phone: String,
        email: String,
        contactName: String,
        contactPhone: String,
        contactEmail: String,
        description: String
    ): ApiResponseStatus<String> =
        repository.addCompany(
            name = name,
            phone = phone,
            email = email,
            contactName = contactName,
            contactPhone = contactPhone,
            contactEmail = contactEmail,
            description = description
        )
}