package com.albertomier.cv_management.profile.data.network.request

import com.google.gson.annotations.SerializedName

data class AddEducationData(
    @SerializedName("centerName") val school: String,
    @SerializedName("title") val title: String,
    @SerializedName("location") val location: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String,
    @SerializedName("description") val description: String
)