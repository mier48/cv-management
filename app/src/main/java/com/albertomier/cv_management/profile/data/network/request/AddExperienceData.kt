package com.albertomier.cv_management.profile.data.network.request

import com.google.gson.annotations.SerializedName

data class AddExperienceData(
    @SerializedName("company") val company: String,
    @SerializedName("jobTitle") val jobTitle: String,
    @SerializedName("location") val location: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("description") val description: String,
)