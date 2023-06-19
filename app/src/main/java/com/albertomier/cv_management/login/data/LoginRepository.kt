package com.albertomier.cv_management.login.data

import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.network.makeNetworkCall
import com.albertomier.cv_management.core.utils.Preferences
import com.albertomier.cv_management.core.utils.SharedPreferenceUtils
import com.albertomier.cv_management.login.data.network.LoginService
import com.albertomier.cv_management.login.domain.model.Login
import com.albertomier.cv_management.login.domain.model.toDomain
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(email: String, password: String): ApiResponseStatus<Login> =
        makeNetworkCall {
            val result = loginService.login(email, password)

            with(SharedPreferenceUtils()) {
                setValue(Preferences.ID, result.id)
                setValue(Preferences.EMAIL, result.email)
                setValue(Preferences.NAME, result.name)
                setValue(Preferences.ACCESS_TOKEN, result.accessToken)
            }

            result.toDomain()
        }

    suspend fun logout() {
        makeNetworkCall {
            loginService.logout()
            SharedPreferenceUtils().clear()
        }
    }
}