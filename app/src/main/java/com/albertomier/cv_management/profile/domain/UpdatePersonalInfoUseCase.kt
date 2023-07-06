package com.albertomier.cv_management.profile.domain

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.profile.data.ProfileRepository
import javax.inject.Inject

class UpdatePersonalInfoUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(
        name: String,
        lastname: String,
        birthdate: String,
        residencePlace: String,
        jobTitle: String,
        email: String,
        phone: String,
        languages: String,
        description: String
    ): ApiResponseStatus<String> =
        repository.updatePersonalData(
            name = name,
            lastname = lastname,
            birthdate = birthdate,
            residencePlace = residencePlace,
            jobTitle = jobTitle,
            email = email,
            phone = phone,
            languages = languages,
            description = description
        )
}