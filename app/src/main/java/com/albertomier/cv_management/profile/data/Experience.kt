package com.albertomier.cv_management.profile.data

data class Experience(
    val company: String,
    val jobTitle: String,
    val location: String,
    val startDate: String,
    val endDate: String,
    val description: String,
    val stillWorking: Boolean = false
)