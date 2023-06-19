package com.albertomier.cv_management.login.domain

import com.albertomier.cv_management.login.data.LoginRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke() {
        repository.logout()
    }
}