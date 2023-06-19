package com.albertomier.cv_management.login.domain

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.login.data.LoginRepository
import com.albertomier.cv_management.login.domain.model.Login
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(email: String, password: String): ApiResponseStatus<Login> =
        repository.login(email, password)
}