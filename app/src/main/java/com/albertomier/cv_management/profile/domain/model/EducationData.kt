package com.albertomier.cv_management.profile.domain.model

import android.os.Parcelable
import com.albertomier.cv_management.profile.data.network.response.EducationDataResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class EducationData(
    val centerName: String,
    val title: String,
    val location: String,
    val startDate: String,
    val endDate: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
) : Parcelable {
    constructor() : this("", "", "", "", "", "", "", "")
}

fun EducationDataResponse.toDomain() = EducationData(
    centerName = centerName,
    title = title,
    location = location,
    startDate = startDate,
    endDate = endDate,
    description = description,
    createdAt = createdAt,
    updatedAt = updatedAt,
)