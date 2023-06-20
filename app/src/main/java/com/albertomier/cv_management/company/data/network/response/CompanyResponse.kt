package com.albertomier.cv_management.company.data.network.response

import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: List<CompanyItemResponse>,
    @SerializedName("message") var message: String,
)