package com.albertomier.cv_management.profile.data.network

import com.albertomier.cv_management.company.data.network.response.SuccessResponse
import com.albertomier.cv_management.core.utils.Utils
import com.albertomier.cv_management.profile.data.network.request.AddExperienceData
import com.albertomier.cv_management.profile.data.network.request.AddPersonalData
import com.albertomier.cv_management.profile.data.network.response.AddExperienceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProfileService @Inject constructor(private val profileClient: ProfileClient) {

    suspend fun addExperience(
        company: String,
        jobTitle: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddExperienceResponse> = profileClient.addExperience(
                Utils.getAuthorization(),
                AddExperienceData(
                    company = company,
                    jobTitle = jobTitle,
                    location = location,
                    startDate = startDate,
                    endDate = endDate,
                    description = description
                )
            )
            response.body()!!.data
        }
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
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddExperienceResponse> = profileClient.addPersonalData(
                Utils.getAuthorization(),
                AddPersonalData(
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
            )
            response.body()!!.data
        }
    }
}