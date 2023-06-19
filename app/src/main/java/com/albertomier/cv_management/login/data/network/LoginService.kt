package com.albertomier.cv_management.login.data.network

import android.util.Log
import com.albertomier.cv_management.login.data.network.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient) {

    suspend fun login(email: String, password: String): LoginResponse {
        return withContext(Dispatchers.IO) {
            Log.e("TAG", "$email|$password")
            val response: Response<LoginResponse> = loginClient.login(LoginData(email, password))
            Log.e("TAG", "|$response|")
            response.body()!!
        }
    }

    suspend fun logout() {
        withContext(Dispatchers.IO) { loginClient.logout() }
    }
}