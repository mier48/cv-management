package com.albertomier.cv_management.company.data.network.response

import com.google.gson.annotations.SerializedName

data class InterviewItemResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("companyId") var companyId: Int,
    @SerializedName("date") var date: String,
    @SerializedName("hour") var hour: String,
    @SerializedName("comment") var comment: String,
    @SerializedName("done") var done: Int,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("updatedAt") var updatedAt: String
)