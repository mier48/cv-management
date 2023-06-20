package com.albertomier.cv_management.company.domain

import com.albertomier.cv_management.company.data.CompanyRepository
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.core.network.ApiResponseStatus
import javax.inject.Inject

class GetCompanyUseCase @Inject constructor(private val repository: CompanyRepository) {

    suspend operator fun invoke(): ApiResponseStatus<List<CompanyItem>> =
        repository.getCompanyList()
}