package com.albertomier.cv_management.profile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.core.extensions.isValidEmail
import com.albertomier.cv_management.core.network.ApiResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _lastname = MutableLiveData<String>()
    val lastname: LiveData<String> = _lastname

    private val _birthdate = MutableLiveData<String>()
    val birthdate: LiveData<String> = _birthdate

    private val _residencePlace = MutableLiveData<String>()
    val residencePlace: LiveData<String> = _residencePlace

    private val _jobTitle = MutableLiveData<String>()
    val jobTitle: LiveData<String> = _jobTitle

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _languages = MutableLiveData<String>()
    val languages: LiveData<String> = _languages

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _isSaveDataEnabled = MutableLiveData<Boolean>()
    val isSaveDataEnabled: LiveData<Boolean> = _isSaveDataEnabled

    fun onDataChanged(
        name: String,
        lastname: String,
        birthdate: String,
        residencePlace: String,
        jobTitle: String,
        email: String,
        phone: String,
        languages: String,
        description: String
    ) {
        _name.value = name
        _lastname.value = lastname
        _birthdate.value = birthdate
        _residencePlace.value = residencePlace
        _jobTitle.value = jobTitle
        _email.value = email
        _phone.value = phone
        _languages.value = languages
        _description.value = description

        _isSaveDataEnabled.value = name.isNotEmpty()
                && lastname.isNotEmpty()
                && birthdate.isNotEmpty()
                && residencePlace.isValidEmail()
                && jobTitle.isNotEmpty()
                && email.isNotEmpty()
                && phone.isNotEmpty()
                && languages.isValidEmail()
                && description.isNotEmpty()
    }

    fun saveData(
        name: String,
        lastname: String,
        birthdate: String,
        residencePlace: String,
        jobTitle: String,
        email: String,
        phone: String,
        languages: String,
        description: String
    ) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusAddResponse(responseStatus: ApiResponseStatus<String>) {
        _status.value = responseStatus as ApiResponseStatus<Any>
    }
}