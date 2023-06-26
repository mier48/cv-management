package com.albertomier.cv_management.company.domain.model

import android.os.Parcelable
import com.albertomier.cv_management.company.data.network.response.CompanyItemResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyItem(
    val id: Int,
    val name: String,
    val phone: String,
    val email: String,
    val contactName: String,
    val contactPhone: String,
    val contactEmail: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String
) : Parcelable {
    constructor() : this(0, "", "", "", "", "", "", "", "", "")
}

fun CompanyItemResponse.toDomain() = CompanyItem(
    id = id,
    name = name,
    phone = phone,
    email = email,
    contactName = contactName,
    contactPhone = contactPhone,
    contactEmail = contactEmail,
    description = description,
    createdAt = createdAt,
    updatedAt = updatedAt
)