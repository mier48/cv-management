package com.albertomier.cv_management.company.domain.model

import android.os.Parcelable
import com.albertomier.cv_management.company.data.network.response.InterviewItemResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class InterviewItem(
    val id: Int,
    val companyId: Int,
    val date: String,
    val hour: String,
    val comment: String,
    val done: Int,
    val createdAt: String,
    val updatedAt: String
) : Parcelable {
    constructor() : this(0, 0, "", "", "", 0, "", "")
}

fun InterviewItemResponse.toDomain() = InterviewItem(
    id = id,
    companyId = companyId,
    date = date,
    hour = hour,
    comment = comment,
    done = done,
    createdAt = createdAt,
    updatedAt = updatedAt
)