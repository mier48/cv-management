package com.albertomier.cv_management.profile.data.network

import com.albertomier.cv_management.company.data.network.response.SuccessResponse
import com.albertomier.cv_management.core.utils.Utils
import com.albertomier.cv_management.profile.data.network.request.AddEducationData
import com.albertomier.cv_management.profile.data.network.request.AddExperienceData
import com.albertomier.cv_management.profile.data.network.request.AddPersonalData
import com.albertomier.cv_management.profile.data.network.response.AddEducationResponse
import com.albertomier.cv_management.profile.data.network.response.AddExperienceResponse
import com.albertomier.cv_management.profile.data.network.response.EducationDataResponse
import com.albertomier.cv_management.profile.data.network.response.ExperienceDataResponse
import com.albertomier.cv_management.profile.data.network.response.GetEducationDataResponse
import com.albertomier.cv_management.profile.data.network.response.GetExperienceDataResponse
import com.albertomier.cv_management.profile.data.network.response.GetPersonalDataResponse
import com.albertomier.cv_management.profile.data.network.response.PersonalDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProfileService @Inject constructor(private val profileClient: ProfileClient) {

    /**
     * GET DATA
     */
    suspend fun getPersonalData(): PersonalDataResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<GetPersonalDataResponse> =
                profileClient.getPersonalData(Utils.getAuthorization())

            response.body()!!.data
        }
    }

    suspend fun getEducationData(): List<EducationDataResponse> {
        return withContext(Dispatchers.IO) {
            val response: Response<GetEducationDataResponse> =
                profileClient.getEducationData(Utils.getAuthorization())

            response.body()!!.data
        }
    }

    suspend fun getExperienceData(): List<ExperienceDataResponse> {
        return withContext(Dispatchers.IO) {
            val response: Response<GetExperienceDataResponse> =
                profileClient.getExperienceData(Utils.getAuthorization())

            response.body()!!.data
        }
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
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddExperienceResponse> = profileClient.addExperienceData(
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

    suspend fun addEducationData(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddEducationResponse> = profileClient.addEducationData(
                Utils.getAuthorization(),
                AddEducationData(
                    school = school,
                    title = title,
                    location = location,
                    startDate = startDate,
                    endDate = endDate,
                    description = description
                )
            )
            response.body()!!.data
        }
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

    /**
     * UPDATE DATA
     */
    suspend fun updateEducationData(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddEducationResponse> = profileClient.updateEducationData(
                Utils.getAuthorization(),
                AddEducationData(
                    school = school,
                    title = title,
                    location = location,
                    startDate = startDate,
                    endDate = endDate,
                    description = description
                )
            )
            response.body()!!.data
        }
    }

    suspend fun updateExperienceData(
        company: String,
        jobTitle: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddExperienceResponse> = profileClient.updateExperienceData(
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
    ): SuccessResponse {
        return withContext(Dispatchers.IO) {
            val response: Response<AddExperienceResponse> = profileClient.updatePersonalData(
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