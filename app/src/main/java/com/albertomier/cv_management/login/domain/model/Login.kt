package com.albertomier.cv_management.login.domain.model

import android.os.Parcelable
import com.albertomier.cv_management.login.data.network.response.LoginResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    val accessToken: String,
    val tokenType: String,
    val expiresAt: String
) : Parcelable

fun LoginResponse.toDomain() = Login(accessToken, tokenType, expiresAt)