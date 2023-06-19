package com.albertomier.cv_management.login.data.network

import com.google.gson.annotations.SerializedName

data class LoginData (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)