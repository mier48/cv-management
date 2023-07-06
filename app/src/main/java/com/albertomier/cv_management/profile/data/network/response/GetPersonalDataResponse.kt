package com.albertomier.cv_management.profile.data.network.response

import com.google.gson.annotations.SerializedName

data class GetPersonalDataResponse(
    @SerializedName("success") var success: Boolean,
    @SerializedName("data") var data: PersonalDataResponse,
    @SerializedName("message") var message: String,
)