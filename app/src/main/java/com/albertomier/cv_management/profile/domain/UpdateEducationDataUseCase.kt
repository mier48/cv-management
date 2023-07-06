package com.albertomier.cv_management.profile.domain

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.profile.data.ProfileRepository
import javax.inject.Inject

class UpdateEducationDataUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): ApiResponseStatus<String> =
        repository.updateEducationData(
            school = school,
            title = title,
            location = location,
            startDate = startDate,
            endDate = endDate,
            description = description
        )
}