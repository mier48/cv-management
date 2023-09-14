package com.albertomier.cv_management.company.domain

import com.albertomier.cv_management.company.data.CompanyRepository
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.core.network.ApiResponseStatus
import javax.inject.Inject

class GetCompanyByIdUseCase @Inject constructor(private val repository: CompanyRepository) {

    suspend operator fun invoke(id: Int): ApiResponseStatus<CompanyItem?> =
        repository.getCompanyById(id)
}