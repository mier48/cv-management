package com.albertomier.cv_management.company.domain

import com.albertomier.cv_management.company.data.CompanyRepository
import com.albertomier.cv_management.core.network.ApiResponseStatus
import javax.inject.Inject

class AddInterviewUseCase @Inject constructor(private val repository: CompanyRepository) {

    suspend operator fun invoke(
        companyId: Int,
        date: String,
        hour: String,
        comment: String,
        done: Int
    ): ApiResponseStatus<String> =
        repository.addInterview(
            companyId = companyId,
            date = date,
            hour = hour,
            comment = comment,
            done = done
        )
}