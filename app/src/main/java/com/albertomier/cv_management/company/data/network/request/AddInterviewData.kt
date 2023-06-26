package com.albertomier.cv_management.company.data.network.request

import com.google.gson.annotations.SerializedName

data class AddInterviewData(
    @SerializedName("companyId") val companyId: Int,
    @SerializedName("date") val date: String,
    @SerializedName("hour") val hour: String,
    @SerializedName("comment") val comment: String,
    @SerializedName("done") val done: Int,
)