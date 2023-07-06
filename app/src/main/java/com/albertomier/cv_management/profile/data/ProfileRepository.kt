package com.albertomier.cv_management.profile.data

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.network.makeNetworkCall
import com.albertomier.cv_management.profile.data.network.ProfileService
import com.albertomier.cv_management.profile.domain.model.EducationData
import com.albertomier.cv_management.profile.domain.model.ExperienceData
import com.albertomier.cv_management.profile.domain.model.PersonalData
import com.albertomier.cv_management.profile.domain.model.toDomain
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileService: ProfileService) {

    /**
     * GET DATA
     */
    suspend fun getPersonalData(): ApiResponseStatus<PersonalData> =
        makeNetworkCall {
            val result = profileService.getPersonalData()
            result.toDomain()
        }

    suspend fun getEducationData(): ApiResponseStatus<List<EducationData>> =
        makeNetworkCall {
            val result = profileService.getEducationData()
            result.map { it.toDomain() }
        }

    suspend fun getExperienceData(): ApiResponseStatus<List<ExperienceData>> =
        makeNetworkCall {
            val result = profileService.getExperienceData()
            result.map { it.toDomain() }
        }

    /**
     * ADD DATA
     */
    suspend fun addExperienceData(
        company: String,
        jobTitle: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): ApiResponseStatus<String> =
        makeNetworkCall {
            profileService.addExperienceData(
                company = company,
                jobTitle = jobTitle,
                location = location,
                startDate = startDate,
                endDate = endDate,
                description = description
            ).response
        }

    suspend fun addEducationData(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): ApiResponseStatus<String> =
        makeNetworkCall {
            profileService.addEducationData(
                school = school,
                title = title,
                location = location,
                startDate = startDate,
                endDate = endDate,
                description = description
            ).response
        }

    suspend fun updateEducationData(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): ApiResponseStatus<String> =
        makeNetworkCall {
            profileService.updateEducationData(
                school = school,
                title = title,
                location = location,
                startDate = startDate,
                endDate = endDate,
                description = description
            ).response
        }

    suspend fun addPersonalData(
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
            profileService.addPersonalData(
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

    suspend fun updatePersonalData(
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
            profileService.updatePersonalData(
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