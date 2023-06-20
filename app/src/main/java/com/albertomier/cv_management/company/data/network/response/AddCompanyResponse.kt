package com.albertomier.cv_management.company.data.network.response

import com.google.gson.annotations.SerializedName

data class AddCompanyResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: SuccessResponse,
    @SerializedName("message") var message: String,
)