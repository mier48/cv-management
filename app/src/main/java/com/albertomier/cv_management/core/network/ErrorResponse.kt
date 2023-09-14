package com.albertomier.cv_management.core.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String
)