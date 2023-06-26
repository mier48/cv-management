package com.albertomier.cv_management.company.data.network.request

import com.google.gson.annotations.SerializedName

data class AddCompanyData(
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("email") val email: String,
    @SerializedName("contactName") val contactName: String,
    @SerializedName("contactPhone") val contactPhone: String,
    @SerializedName("contactEmail") val contactEmail: String,
    @SerializedName("description") val description: String,
)