package com.albertomier.cv_management.profile.data.network.response

import com.google.gson.annotations.SerializedName

data class GetEducationDataResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: List<EducationDataResponse>,
    @SerializedName("message") var message: String,
)