package com.albertomier.cv_management.profile.data.network.response

import com.albertomier.cv_management.company.data.network.response.SuccessResponse
import com.google.gson.annotations.SerializedName

data class AddEducationResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: SuccessResponse,
    @SerializedName("message") var message: String,
)