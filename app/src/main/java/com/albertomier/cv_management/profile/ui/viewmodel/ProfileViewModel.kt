package com.albertomier.cv_management.profile.ui.viewmodel

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.print.PdfConverter
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.cv_management.core.extensions.isValidEmail
import com.albertomier.cv_management.core.network.ApiResponseStatus
import com.albertomier.cv_management.core.utils.AddOrRemoveAction
import com.albertomier.cv_management.core.utils.LoadingState
import com.albertomier.cv_management.core.utils.Utils.convertTimeStampToDate
import com.albertomier.cv_management.main.data.SheetContentState
import com.albertomier.cv_management.profile.domain.AddEducationDataUseCase
import com.albertomier.cv_management.profile.domain.AddExperienceUseCase
import com.albertomier.cv_management.profile.domain.AddPersonalDataUseCase
import com.albertomier.cv_management.profile.domain.GetEducationDataUseCase
import com.albertomier.cv_management.profile.domain.GetExperienceDataUseCase
import com.albertomier.cv_management.profile.domain.GetPersonalDataUseCase
import com.albertomier.cv_management.profile.domain.UpdateEducationDataUseCase
import com.albertomier.cv_management.profile.domain.UpdateExperienceDataUseCase
import com.albertomier.cv_management.profile.domain.UpdatePersonalInfoUseCase
import com.albertomier.cv_management.profile.domain.model.EducationData
import com.albertomier.cv_management.profile.domain.model.ExperienceData
import com.albertomier.cv_management.profile.domain.model.PersonalData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
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
    private val updateEducationDataUseCase: UpdateEducationDataUseCase,
    private val updateExperienceDataUseCase: UpdateExperienceDataUseCase
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

    /**
     * HTML
     */
    private val _htmlContent = MutableLiveData<String>()
    val htmlContent: LiveData<String> = _htmlContent

    private var _creatingCVState = MutableStateFlow(LoadingState.IDLE)
    var creatingCVState: StateFlow<LoadingState> = _creatingCVState

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

    fun updateExperienceData(
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
                updateExperienceDataUseCase(
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
                updateEducationDataUseCase(
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

    fun showEducationData(educationData: EducationData) {
        _edSchool.value = educationData.centerName
        _edTitle.value = educationData.title
        _edLocation.value = educationData.location
        _edStartDate.value = educationData.startDate
        _edEndDate.value = educationData.endDate
        _edDescription.value = educationData.description

        _isSaveExperienceDataEnabled.value = educationData.centerName.isNotEmpty()
                && educationData.title.isNotEmpty()
                && educationData.location.isNotEmpty()
                && educationData.startDate.isNotEmpty()
                && educationData.endDate.isNotEmpty()
                && educationData.description.isNotEmpty()
    }

    fun generateV2() {
        val htmlContent = StringBuffer()
        htmlContent.append(

                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<title>Resume</title>\n" +
                        "<meta charset=UTF-8>\n" +
                        "<link rel=\"shortcut icon\" href=https://ssl.gstatic.com/docs/documents/images/kix-favicon6.ico>\n" +
                        "<style type=text/css>html,body { height:297mm; width:210mm; }body{font-family:arial,sans,sans-serif;margin:0}iframe{border:0;frameborder:0;height:100%%;width:100%%}#header,#footer{background:#f0f0f0;padding:10px 10px}#header{border-bottom:1px #ccc solid}#footer{border-top:1px #ccc solid;border-bottom:1px #ccc solid;font-size:13}#contents{margin:6px}.dash{padding:0 6px}</style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div id=contents>\n" +
                        "<style type=text/css>@import url('https://themes.googleusercontent.com/fonts/css?kit=xTOoZr6X-i3kNg7pYrzMsnEzyYBuwf3lO_Sc3Mw9RUVbV0WvE1cEyAoIq5yYZlSc');ol{margin:0;padding:0}table td,table th{padding:0}.c26{border-right-style:solid;padding:3.6pt 3.6pt 3.6pt 3.6pt;border-bottom-color:#fff;border-top-width:0;border-right-width:0;border-left-color:#fff;" +
                        "vertical-align:top;border-right-color:#fff;border-left-width:0;border-top-style:solid;border-left-style:solid;border-bottom-width:0;width:176.3pt;border-top-color:#fff;border-bottom-style:solid}.c4{border-right-style:solid;padding:5pt 5pt 5pt 5pt;border-bottom-color:#fff;border-top-width:0;border-right-width:0;border-left-color:#fff;vertical-align:top;" +
                        "border-right-color:#fff;border-left-width:0;border-top-style:solid;border-left-style:solid;border-bottom-width:0;width:327.7pt;border-top-color:#fff;border-bottom-style:solid}.c16{color:#000;font-weight:700;text-decoration:none;vertical-align:baseline;font-size:12pt;font-family:\"Raleway\";font-style:normal}.c7{color:#000;font-weight:400;text-decoration:none;" +
                        "vertical-align:baseline;font-size:10pt;font-family:\"Lato\";font-style:normal}.c1{color:#666;font-weight:400;text-decoration:none;vertical-align:baseline;font-size:9pt;font-family:\"Lato\";font-style:normal}.c19{color:#000;" +
                        "font-weight:400;text-decoration:none;vertical-align:baseline;font-size:6pt;font-family:\"Lato\";font-style:normal}.c20{color:#f2511b;font-weight:700;text-decoration:none;vertical-align:baseline;font-size:16pt;font-family:\"Raleway\";font-style:normal}.c6{padding-top:0;padding-bottom:0;line-height:1.0;text-align:left}.c32{padding-top:5pt;padding-bottom:0;" +
                        "line-height:1.15;text-align:left}.c0{padding-top:10pt;padding-bottom:0;line-height:1.0;text-align:left}.c10{color:#d44500;text-decoration:none;vertical-align:baseline;font-style:normal}.c2{padding-top:0;padding-bottom:0;line-height:1.15;text-align:left}.c33{padding-top:3pt;padding-bottom:0;" +
                        "line-height:1.0;text-align:left}.c9{padding-top:4pt;padding-bottom:0;line-height:1.15;text-align:left}.c23{border-spacing:0;border-collapse:collapse;margin:0 auto}.c30{color:#000;text-decoration:none;vertical-align:baseline;font-style:normal}.c3{padding-top:6pt;padding-bottom:0;line-height:1.15;text-align:left}.c14{padding-top:16pt;padding-bottom:0;" +
                        "line-height:1.15;text-align:left}</style>\n" +
                        "<style>.c24{font-size:14pt;font-family:\"Lato\";font-weight:700}.c8{font-size:10pt;font-family:\"Lato\";font-weight:400}.c5{font-size:11pt;font-family:\"Lato\";font-weight:400}\"" +
                        ".c11{orphans:2;widows:2;height:11pt}.c21{height:auto}.c15{height:auto}.c27{height:auto}.c29{height:auto}.c12{page-break-after:avoid}.title{padding-top:6pt;color:#000;font-weight:700;font-size:24pt;" +
                        "padding-bottom:0;font-family:\"Raleway\";line-height:1.0;page-break-after:avoid;orphans:2;widows:2;text-align:left}.subtitle{padding-top:3pt;color:#f2511b;font-weight:700;font-size:16pt;padding-bottom:0;font-family:\"Raleway\";line-height:1.0;page-break-after:avoid;orphans:2;widows:2;text-align:left}li{color:#000;font-size:11pt;" +
                        "font-family:\"Lato\"}p{margin:0;color:#000;font-size:11pt;font-family:\"Lato\"}h1{padding-top:4pt;color:#000;font-weight:700;font-size:12pt;padding-bottom:0;font-family:\"Raleway\";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h2{padding-top:6pt;color:#000;font-weight:700;font-size:11pt;padding-bottom:0;font-family:\"Lato\";" +
                        "line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h3{padding-top:6pt;color:#666;font-size:9pt;padding-bottom:0;font-family:\"Lato\";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h4{padding-top:8pt;-webkit-text-decoration-skip:none;color:#666;text-decoration:underline;font-size:11pt;padding-bottom:0;" +
                        "line-height:1.15;page-break-after:avoid;text-decoration-skip-ink:none;font-family:\"Trebuchet MS\";orphans:2;widows:2;text-align:left}h5{padding-top:8pt;color:#666;font-size:11pt;padding-bottom:0;font-family:\"Trebuchet MS\";line-height:1.15;page-break-after:avoid;orphans:2;widows:2;text-align:left}h6{padding-top:8pt;color:#666;font-size:11pt;" +
                        "padding-bottom:0;font-family:\"Trebuchet MS\";line-height:1.15;page-break-after:avoid;font-style:italic;orphans:2;widows:2;text-align:left} </style>\n" +
                        "<p class=\"c2 c29\"><span class=c19></span></p>\n" +
                        "<a id=t.b7144d62fc47a2bfcf177a3c3dd72df0e868051e></a>\n" +
                        "<a id=t.0></a>\n" +
                        "<table class=c23>\n" +
                        "            <tbody>\n" +
                        "                <tr class=\"c21\">\n" +
                        "                    <td class=\"c26\" colspan=\"1\" rowspan=\"1\">\n" +
                        "                        <p class=\"c6\"><span style=\"overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px);\"><img alt=\"\" src=\"%s\" style=\"width: 132px; height: 170px; margin-left: 0.00px; margin-top: 0.00px; " +
                        "transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px);\"></span></p>\n" +
                        "                    </td>\n" +
                        "                    <td class=\"c4\" colspan=\"1\" rowspan=\"1\">\n" +
                        "                        <h1 class=\"c6 c12 title\" id=\"h.4prkjmzco10w\"><span>${_name.value} ${_lastname.value}</span></h1>\n" +
                        "                        <p class=\"c33 subtitle\" id=\"h.o2iwx3vdck7p\"><span class=\"c20\">${_jobTitle.value}</span></p>\n" +
                        "                        <p class=\"c6 c0\"><span class=\"c7\">${_birthdate.value} in ${_residencePlace.value}</span></p>\n" +
                        "                        <p class=\"c6 c0\"><span class=\"c7\">${_phone.value}</span></p>\n" +
                        "                        <p class=\"c0\"><span class=\"c10 c8\">${_email.value}</span></p>\n" +
                        "                    </td>\n" +
                        "                </tr>"
        )

//        if (cvInfo.value.skills.isNotEmpty()) {
//            htmlContent.append(
//                String.format(
//                    (("\n" +
//                            "                <tr class=\"c27\">\n" +
//                            "                    <td class=\"c26\" colspan=\"1\" rowspan=\"1\">\n" +
//                            "                        <p class=\"c6\"><span class=\"c24\">ㅡ</span></p>\n" +
//                            "                        <h1 class=\"c9\" id=\"h.61e3cm1p1fln\"><span class=\"c16\">" +
//                            "Skills") + "</span></h1></td>\n" +
//                            "                    <td class=\"c4\" colspan=\"1\" rowspan=\"1\">\n" +
//                            "                        <p class=\"c2\"><span style=\"overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px); width: 418.00px; " +
//                            "height: 2.67px;\"><img alt=\"\" src=\"https://lh3.googleusercontent.com/n8bZfGajkthDbPpbjeiRJ4w7rNUmj1iFxdZKCHUOVnfH9FgHVt5EBo3vOYIIoE3augYQ_DCZJUzdlStyJ5RaldVrSG36sTE0CjIot2qaiJ3YRyr2i87bt9Y9d0ngdseS9PpG0HzM\" style=\"width: 418.00px; height: 2.67px; " +
//                            "margin-left: 0.00px; margin-top: 0.00px; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px);\" title=\"horizontal line\"></span></p>\n" +
//                            "                        <p class=\"c3\"><span class=\"c7\">%s</span></p>\n" +
//                            "                    </td>\n" +
//                            "                </tr>"), cvInfo.value.skills
//                )
//            )
//        }
//        if (cvInfo.value.languages.isNotEmpty()) {
//            htmlContent.append(
//                String.format(
//                    (("\n" +
//                            "                <tr class=\"c27\">\n" +
//                            "                    <td class=\"c26\" colspan=\"1\" rowspan=\"1\">\n" +
//                            "                        <p class=\"c6\"><span class=\"c24\">ㅡ</span></p>\n" +
//                            "                        <h1 class=\"c9\" id=\"h.61e3cm1p1fln\"><span class=\"c16\">" +
//                            "Languages") + "</span></h1></td>\n" +
//                            "                    <td class=\"c4\" colspan=\"1\" rowspan=\"1\">\n" +
//                            "                        <p class=\"c2\"><span style=\"overflow: hidden; display: inline-block; margin: 0.00px 0.00px; border: 0.00px solid #000000; transform: rotate(0.00rad) translateZ(0px); " +
//                            "-webkit-transform: rotate(0.00rad) translateZ(0px); width: 418.00px; height: 2.67px;\"><img alt=\"\" src=\"https://lh3.googleusercontent.com/n8bZfGajkthDbPpbjeiRJ4w7rNUmj1iFxdZKCHUOVnfH9FgHVt5EBo3vOYIIoE3augYQ_DCZJUzdlStyJ5RaldVrSG36sTE0CjIot2qaiJ3YRyr2i87bt9Y9d0ngdseS9PpG0HzM\" " +
//                            "style=\"width: 418.00px; height: 2.67px; margin-left: 0.00px; margin-top: 0.00px; transform: rotate(0.00rad) translateZ(0px); -webkit-transform: rotate(0.00rad) translateZ(0px);\" title=\"horizontal line\"></span></p>\n" +
//                            "                        <p class=\"c3\"><span class=\"c7\">%s</span></p>\n" +
//                            "                    </td>\n" +
//                            "                </tr>"), cvInfo.value.languages
//                )
//            )
//        }
//        if (cvInfo.value.education.isNotEmpty()) {
//            htmlContent.append(
//                (("\n" +
//                        "                <tr class=\"c15\">\n" +
//                        "                    <td class=\"c26\" colspan=\"1\" rowspan=\"1\">\n" +
//                        "                        <p class=\"c6\"><span class=\"c24\">ㅡ</span></p>\n" +
//                        "                        <h1 class=\"c9\" id=\"h.tk538brb1kdf\"><span class=\"c16\">" +
//                        application.resources.getString(R.string.education)) + "</span></h1></td>\n" +
//                        "                    <td class=\"c4\" colspan=\"1\" rowspan=\"1\">\n")
//            )
//
//            var first = true
//            cvInfo.value.education.forEach {
//                htmlContent.append(
//                    String.format(
//                        ("<h2 class=\"%s\" id=\"h.u3uy0857ab2n\"><span class=\"c5\">%s </span><span class=\"c30 c5\">/ %s</span></h2>\n" +
//                                "                        <h3 class=\"c2\" id=\"h.re1qtuma0rpm\"><span class=\"c1\">%s</span></h3>\n" +
//                                "                        <p class=\"c32\"><span class=\"c7\">%s</span></p>\n"),
//                        if (first) "c3" else "c14",
//                        it.school,
//                        it.field,
//                        it.diploma,
//                        it.yearOfDiploma
//                    )
//                )
//                first = false
//            }
//            htmlContent.append(
//                "</td>\n" +
//                        "                </tr>"
//            )
//        }
//        if (cvInfo.value.experience.isNotEmpty()) {
//            htmlContent.append(
//                (("\n" +
//                        "                <tr class=\"c15\">\n" +
//                        "                    <td class=\"c26\" colspan=\"1\" rowspan=\"1\">\n" +
//                        "                        <p class=\"c6\"><span class=\"c24\">ㅡ</span></p>\n" +
//                        "                        <h1 class=\"c9\" id=\"h.tk538brb1kdf\"><span class=\"c16\">" +
//                        application.resources.getString(R.string.experience)) + "</span></h1></td>\n" +
//                        "                    <td class=\"c4\" colspan=\"1\" rowspan=\"1\">\n")
//            )
//            var first = true
//
//            cvInfo.value.experience.forEach {
//                htmlContent.append(
//                    String.format(
//                        ("<h2 class=\"%s\" id=\"h.u3uy0857ab2n\"><span class=\"c5\">%s </span><span class=\"c30 c5\">/ %s</span></h2>\n" +
//                                "                        <h3 class=\"c2\" id=\"h.re1qtuma0rpm\"><span class=\"c1\">%s</span><span class=\"c1\">%s</span></h3>\n"
//                                + "                        <p class=\"c32\"><span class=\"c7\">%s</span></p>\n"
//                                ),
//                        if (first) "c3" else "c14",
//                        it.employer,
//                        it.position,
//                        it.fromYear,
//                        if (it.stillWorking) " - still working" else " to ${it.endYear}",
//                        it.comment
//                    )
//                )
//                first = false
//            }
//            htmlContent.append(
//                "</td>\n" +
//                        "                </tr>"
//            )
//        }
        htmlContent.append(
            ("</tbody>\n" +
                    "</table>\n" +
                    "<p class=\"c2 c11\"><span class=\"c30 c5\"></span></p>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>")
        )
        Log.d("Tag", htmlContent.toString())
        setHTMLContent(htmlContent.toString())
    }

    private fun setHTMLContent(htmlString: String) {
        _htmlContent.value = htmlString
    }

    fun saveCVFile(context: Context, htmlString: String, onExportFinished: (Uri) -> Unit) {
        viewModelScope.launch {
            _creatingCVState.emit(LoadingState.LOADING)
            try {
                val fileName = convertTimeStampToDate(System.currentTimeMillis())
                val pdfPath =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        .toString()
                val pdfFile = File(pdfPath, "$fileName.pdf")

                PdfConverter.instance?.convert(
                    context = context,
                    htmlString = htmlString,
                    file = pdfFile,
                    onExportFinished = {
                        val uri: Uri = Uri.fromFile(pdfFile)
                        onExportFinished(uri)
                    })
                _creatingCVState.emit(LoadingState.LOADED)
            } catch (e: Exception) {
                e.printStackTrace()
                _creatingCVState.emit(LoadingState.ERROR)
            }
        }
    }
}