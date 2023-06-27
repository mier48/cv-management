package com.albertomier.cv_management.profile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.cv_management.core.extensions.isValidEmail
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.main.data.SheetContentState
import com.albertomier.cv_management.profile.data.Experience
import com.albertomier.cv_management.profile.domain.AddExperienceUseCase
import com.albertomier.cv_management.profile.domain.AddPersonalInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val addExperienceUseCase: AddExperienceUseCase,
    private val addPersonalInfoUseCase: AddPersonalInfoUseCase
) : ViewModel() {

    private var _sheetStateContent: MutableStateFlow<SheetContentState> =
        MutableStateFlow(SheetContentState.ADD)
    val sheetStateContent: StateFlow<SheetContentState> = _sheetStateContent

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

    /**
     * Personal
     */
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

    /**
     * Education
     */
    private val _school = MutableLiveData<String>()
    val school: LiveData<String> = _school

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate

    private val _isSavePersonalDataEnabled = MutableLiveData<Boolean>()
    val isSavePersonalDataEnabled: LiveData<Boolean> = _isSavePersonalDataEnabled

    /**
     * Experience
     */
    private val _exCompanyName = MutableLiveData<String>()
    val exCompanyName: LiveData<String> = _exCompanyName

    private val _exJobTitle = MutableLiveData<String>()
    val exJobTitle: LiveData<String> = _exJobTitle

    private val _exLocation = MutableLiveData<String>()
    val exLocation: LiveData<String> = _exLocation

    private val _exStartDate = MutableLiveData<String>()
    val exStartDate: LiveData<String> = _exStartDate

    private val _exEndDate = MutableLiveData<String>()
    val exEndDate: LiveData<String> = _exEndDate

    private val _exDescription = MutableLiveData<String>()
    val exDescription: LiveData<String> = _exDescription

    private var _experienceList = MutableLiveData<List<Experience>>()
    val experienceList: LiveData<List<Experience>> = _experienceList

    private val _isSaveExperienceDataEnabled = MutableLiveData<Boolean>()
    val isSaveExperienceDataEnabled: LiveData<Boolean> = _isSaveExperienceDataEnabled


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

    fun onExperienceDataChanged(
        company: String,
        jobTitle: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ) {
        _exCompanyName.value = company
        _exJobTitle.value = jobTitle
        _exLocation.value = location
        _exStartDate.value = startDate
        _exEndDate.value = endDate
        _exDescription.value = description

        _isSaveExperienceDataEnabled.value = company.isNotEmpty()
                && jobTitle.isNotEmpty()
                && location.isNotEmpty()
                && startDate.isNotEmpty()
                && endDate.isNotEmpty()
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
            handleResponseStatusAddResponse(
                addPersonalInfoUseCase(
                    name = name,
                    lastname = lastname,
                    birthdate = birthdate,
                    residencePlace = residencePlace,
                    jobTitle = jobTitle,
                    email = email,
                    phone = phone,
                    languages = languages,
                    description = description
                )
            )
        }
    }

    fun setSheetStateContent(sheetStateContent: SheetContentState) {
        _sheetStateContent.value = sheetStateContent
    }

    fun resetExperienceFields() {
        _exCompanyName.value = ""
        _exJobTitle.value = ""
        _exLocation.value = ""
        _exStartDate.value = ""
        _exEndDate.value = ""
        _exDescription.value = ""
        _isSaveExperienceDataEnabled.value = false
    }

    fun saveExperienceData(
        company: String,
        jobTitle: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatusAddResponse(
                addExperienceUseCase(
                    company = company,
                    jobTitle = jobTitle,
                    location = location,
                    startDate = startDate,
                    endDate = endDate,
                    description = description
                )
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusAddResponse(responseStatus: ApiResponseStatus<String>) {
        _status.value = responseStatus as ApiResponseStatus<Any>
    }
}