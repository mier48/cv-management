package com.albertomier.cv_management.profile.domain

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.profile.data.ProfileRepository
import javax.inject.Inject

class UpdateExperienceDataUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(
        company: String,
        jobTitle: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): ApiResponseStatus<String> =
        repository.updateExperienceData(
            company = company,
            jobTitle = jobTitle,
            location = location,
            startDate = startDate,
            endDate = endDate,
            description = description
        )
}