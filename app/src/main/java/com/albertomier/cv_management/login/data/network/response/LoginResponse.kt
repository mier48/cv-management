package com.albertomier.cv_management.login.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id") var id: String,
    @SerializedName("email") var email: String,
    @SerializedName("name") var name: String,
    @SerializedName("access_token") var accessToken: String,
    @SerializedName("token_type") var tokenType: String,
    @SerializedName("expires_at") var expiresAt: String
)