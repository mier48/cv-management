package com.albertomier.cv_management.profile.domain

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.profile.data.ProfileRepository
import com.albertomier.cv_management.profile.domain.model.PersonalData
import javax.inject.Inject

class GetPersonalDataUseCase @Inject constructor(private val repository: ProfileRepository) {

    suspend operator fun invoke(): ApiResponseStatus<PersonalData> = repository.getPersonalData()
}