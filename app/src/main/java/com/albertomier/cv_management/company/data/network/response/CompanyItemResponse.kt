package com.albertomier.cv_management.company.data.network.response

import com.google.gson.annotations.SerializedName

data class CompanyItemResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("phone") var phone: String,
    @SerializedName("email") var email: String,
    @SerializedName("contactName") var contactName: String,
    @SerializedName("contactPhone") var contactPhone: String,
    @SerializedName("contactEmail") var contactEmail: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("updatedAt") var updatedAt: String
)