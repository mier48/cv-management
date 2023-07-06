package com.albertomier.cv_management.profile.domain.model

import android.os.Parcelable
import com.albertomier.cv_management.profile.data.network.response.EducationDataResponse
import com.albertomier.cv_management.profile.data.network.response.ExperienceDataResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExperienceData(
    val company: String,
    val jobTitle: String,
    val location: String,
    val startDate: String,
    val endDate: String,
    val description: String,
) : Parcelable {
    constructor() : this("", "", "", "", "", "")
}

fun ExperienceDataResponse.toDomain() = ExperienceData(
    company = company,
    jobTitle = jobTitle,
    location = location,
    startDate = startDate,
    endDate = endDate,
    description = description
)