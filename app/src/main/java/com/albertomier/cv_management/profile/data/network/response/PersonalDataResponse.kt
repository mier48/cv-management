package com.albertomier.cv_management.profile.data.network.response

import com.google.gson.annotations.SerializedName

data class PersonalDataResponse(
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("birthdate") val birthdate: String,
    @SerializedName("residencePlace") val residencePlace: String,
    @SerializedName("jobTitle") val jobTitle: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("languages") val languages: String,
    @SerializedName("description") val description: String,
)