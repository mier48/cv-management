package com.albertomier.cv_management.company.data.network.response

import com.google.gson.annotations.SerializedName

data class InterviewResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: List<InterviewItemResponse>,
    @SerializedName("message") var message: String,
)