package com.albertomier.cv_management.login.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.utils.Utils.enableLogin
import com.albertomier.cv_management.core.utils.Utils.enableRegister
import com.albertomier.cv_management.login.domain.LoginUseCase
import com.albertomier.cv_management.login.domain.LogoutUseCase
import com.albertomier.cv_management.login.domain.model.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    private val _isRegisterEnabled = MutableLiveData<Boolean>()
    val isRegisterEnabled: LiveData<Boolean> = _isRegisterEnabled

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

    private val _loginData = MutableLiveData<ApiResponseStatus<Login>>()
    val loginData: LiveData<ApiResponseStatus<Login>> get() = _loginData

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password

        _isLoginEnabled.value = enableLogin(email, password)
    }

    fun onRegisterChanged(name: String, email: String, password: String, confirmPassword: String) {
        _name.value = name
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword

        _isRegisterEnabled.value = enableRegister(name, email, password, confirmPassword)
    }

    fun makeLogin(email: String, password: String) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(loginUseCase(email, password))
        }
    }

    fun makeLogout() {
        viewModelScope.launch { logoutUseCase() }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(responseStatus: ApiResponseStatus<Login>) {
        if (responseStatus is ApiResponseStatus.Success) {
            //_loginData.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }
}