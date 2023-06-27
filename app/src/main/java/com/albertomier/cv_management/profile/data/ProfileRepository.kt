package com.albertomier.cv_management.profile.data

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.network.makeNetworkCall
import com.albertomier.cv_management.profile.data.network.ProfileService
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileService: ProfileService) {

    suspend fun addExperience(
        company: String,
        jobTitle: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): ApiResponseStatus<String> =
        makeNetworkCall {
            profileService.addExperience(
                company = company,
                jobTitle = jobTitle,
                location = location,
                startDate = startDate,
                endDate = endDate,
                description = description
            ).response
        }

    suspend fun addPersonalInfo(
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
        makeNetworkCall {
            profileService.addPersonalInfo(
                name = name,
                lastname = lastname,
                birthdate = birthdate,
                residencePlace = residencePlace,
                jobTitle = jobTitle,
                email = email,
                phone = phone,
                languages = languages,
                description = description
            ).response
        }
}