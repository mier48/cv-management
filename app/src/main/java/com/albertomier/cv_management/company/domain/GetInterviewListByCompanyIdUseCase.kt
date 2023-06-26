package com.albertomier.cv_management.company.domain

import com.albertomier.cv_management.company.data.CompanyRepository
import com.albertomier.cv_management.company.domain.model.InterviewItem
import com.albertomier.cv_management.core.network.ApiResponseStatus
import javax.inject.Inject

class GetInterviewListByCompanyIdUseCase @Inject constructor(private val repository: CompanyRepository) {

    suspend operator fun invoke(companyId: Int): ApiResponseStatus<List<InterviewItem>> =
        repository.getInterviewListByCompanyId(companyId)
}