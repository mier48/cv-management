package com.albertomier.cv_management.company.data.network.response

import com.google.gson.annotations.SerializedName

data class SuccessResponse(
    @SerializedName("response") var response: String,
)