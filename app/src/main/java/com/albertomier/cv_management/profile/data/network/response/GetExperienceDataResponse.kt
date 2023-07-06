package com.albertomier.cv_management.profile.data.network.response

import com.google.gson.annotations.SerializedName

data class GetExperienceDataResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: List<ExperienceDataResponse>,
    @SerializedName("message") var message: String,
)