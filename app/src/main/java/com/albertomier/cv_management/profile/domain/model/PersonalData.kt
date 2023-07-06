package com.albertomier.cv_management.profile.domain.model

import android.os.Parcelable
import com.albertomier.cv_management.profile.data.network.response.PersonalDataResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalData(
    val name: String,
    val lastname: String,
    val birthdate: String,
    val residencePlace: String,
    val jobTitle: String,
    val email: String,
    val phone: String,
    val languages: String,
    val description: String
) : Parcelable {
    constructor() : this("", "", "", "", "", "", "", "", "")
}

fun PersonalDataResponse.toDomain() = PersonalData(
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