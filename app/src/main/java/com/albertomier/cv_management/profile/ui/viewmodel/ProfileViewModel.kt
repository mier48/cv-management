package com.albertomier.cv_management.profile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.cv_management.core.extensions.isValidEmail
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.main.data.SheetContentState
import com.albertomier.cv_management.profile.domain.AddEducationDataUseCase
import com.albertomier.cv_management.profile.domain.AddExperienceUseCase
import com.albertomier.cv_management.profile.domain.AddPersonalDataUseCase
import com.albertomier.cv_management.profile.domain.GetEducationDataUseCase
import com.albertomier.cv_management.profile.domain.GetExperienceDataUseCase
import com.albertomier.cv_management.profile.domain.GetPersonalDataUseCase
import com.albertomier.cv_management.profile.domain.UpdatePersonalInfoUseCase
import com.albertomier.cv_management.profile.domain.model.EducationData
import com.albertomier.cv_management.profile.domain.model.ExperienceData
import com.albertomier.cv_management.profile.domain.model.PersonalData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getPersonalInfoUseCase: GetPersonalDataUseCase,
    private val getEducationDataUseCase: GetEducationDataUseCase,
    private val getExperienceDataUseCase: GetExperienceDataUseCase,
    private val addExperienceUseCase: AddExperienceUseCase,
    private val addPersonalInfoUseCase: AddPersonalDataUseCase,
    private val addEducationDataUseCase: AddEducationDataUseCase,
    private val updatePersonalInfoUseCase: UpdatePersonalInfoUseCase,
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
    private val _edSchool = MutableLiveData<String>()
    val edSchool: LiveData<String> = _edSchool

    private val _edTitle = MutableLiveData<String>()
    val edTitle: LiveData<String> = _edTitle

    private val _edLocation = MutableLiveData<String>()
    val edLocation: LiveData<String> = _edLocation

    private val _edStartDate = MutableLiveData<String>()
    val edStartDate: LiveData<String> = _edStartDate

    private val _edEndDate = MutableLiveData<String>()
    val edEndDate: LiveData<String> = _edEndDate

    private val _edDescription = MutableLiveData<String>()
    val edDescription: LiveData<String> = _edDescription

    private var _educationList = MutableLiveData<List<EducationData>>()
    val educationList: LiveData<List<EducationData>> = _educationList

    private val _isSaveEducationDataEnabled = MutableLiveData<Boolean>()
    val isSaveEducationDataEnabled: LiveData<Boolean> = _isSaveEducationDataEnabled

    private val _isEducationDataGetFromApi = MutableLiveData<Boolean>()
    val isEducationDataGetFromApi: LiveData<Boolean> = _isEducationDataGetFromApi

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

    private var _experienceList = MutableLiveData<List<ExperienceData>>()
    val experienceList: LiveData<List<ExperienceData>> = _experienceList

    private val _isSaveExperienceDataEnabled = MutableLiveData<Boolean>()
    val isSaveExperienceDataEnabled: LiveData<Boolean> = _isSaveExperienceDataEnabled

    private val _isPersonalDataGetFromApi = MutableLiveData<Boolean>()
    val isPersonalDataGetFromApi: LiveData<Boolean> = _isPersonalDataGetFromApi

    init {
        _isPersonalDataGetFromApi.value = false
        _isEducationDataGetFromApi.value = false
    }

    fun getPersonalData() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handlePersonalDataResponseStatus(getPersonalInfoUseCase())
        }
    }

    fun getEducationData() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleEducationDataResponseStatus(getEducationDataUseCase())
        }
    }

    fun getExperienceData() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleExperienceDataResponseStatus(getExperienceDataUseCase())
        }
    }

    fun onPersonalDataChanged(
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
                && residencePlace.isNotEmpty()
                && jobTitle.isNotEmpty()
                && email.isValidEmail()
                && phone.isNotEmpty()
                && languages.isNotEmpty()
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

    fun onEducationDataChanged(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String,
    ) {
        _edSchool.value = school
        _edTitle.value = title
        _edLocation.value = location
        _edStartDate.value = startDate
        _edEndDate.value = endDate
        _edDescription.value = description

        _isSaveEducationDataEnabled.value = school.isNotEmpty()
                && title.isNotEmpty()
                && location.isNotEmpty()
                && startDate.isNotEmpty()
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

    fun updateData(
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
                updatePersonalInfoUseCase(
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

    fun resetEducationFields() {
        _edSchool.value = ""
        _edTitle.value = ""
        _edStartDate.value = ""
        _edEndDate.value = ""
        _edDescription.value = ""
        _isSaveEducationDataEnabled.value = false
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

    fun saveEducationData(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatusAddResponse(
                addEducationDataUseCase(
                    school = school,
                    title = title,
                    location = location,
                    startDate = startDate,
                    endDate = endDate,
                    description = description
                )
            )
        }
    }

    fun updateEducationData(
        school: String,
        title: String,
        location: String,
        startDate: String,
        endDate: String,
        description: String
    ) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatusAddResponse(
                addEducationDataUseCase(
                    school = school,
                    title = title,
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

    @Suppress("UNCHECKED_CAST")
    private fun handlePersonalDataResponseStatus(responseStatus: ApiResponseStatus<PersonalData>) {
        if (responseStatus is ApiResponseStatus.Success) {
            val data = responseStatus.data

            _name.value = data.name
            _lastname.value = data.lastname
            _birthdate.value = data.birthdate
            _residencePlace.value = data.residencePlace
            _jobTitle.value = data.jobTitle
            _email.value = data.email
            _phone.value = data.phone
            _languages.value = data.languages
            _description.value = data.description
            _isPersonalDataGetFromApi.value = true

            _isSaveDataEnabled.value = data.name.isNotEmpty()
                    && data.lastname.isNotEmpty()
                    && data.birthdate.isNotEmpty()
                    && data.residencePlace.isNotEmpty()
                    && data.jobTitle.isNotEmpty()
                    && data.email.isValidEmail()
                    && data.phone.isNotEmpty()
                    && data.languages.isNotEmpty()
                    && data.description.isNotEmpty()
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleEducationDataResponseStatus(responseStatus: ApiResponseStatus<List<EducationData>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _educationList.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleExperienceDataResponseStatus(responseStatus: ApiResponseStatus<List<ExperienceData>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _experienceList.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    fun showExperienceData(experienceData: ExperienceData) {
        _exCompanyName.value = experienceData.company
        _exJobTitle.value = experienceData.jobTitle
        _exLocation.value = experienceData.location
        _exStartDate.value = experienceData.startDate
        _exEndDate.value = experienceData.endDate
        _exDescription.value = experienceData.description

        _isSaveExperienceDataEnabled.value = experienceData.company.isNotEmpty()
                && experienceData.jobTitle.isNotEmpty()
                && experienceData.location.isNotEmpty()
                && experienceData.startDate.isNotEmpty()
                && experienceData.endDate.isNotEmpty()
                && experienceData.description.isNotEmpty()
    }
}