package com.albertomier.cv_management.company.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.cv_management.company.domain.AddCompanyUseCase
import com.albertomier.cv_management.company.domain.AddInterviewUseCase
import com.albertomier.cv_management.company.domain.GetCompanyByIdUseCase
import com.albertomier.cv_management.company.domain.GetCompanyUseCase
import com.albertomier.cv_management.company.domain.GetInterviewListByCompanyIdUseCase
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.company.domain.model.InterviewItem
import com.albertomier.cv_management.core.extensions.isValidEmail
import com.albertomier.cv_management.core.network.ApiResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val addCompanyUseCase: AddCompanyUseCase,
    private val getCompanyUseCase: GetCompanyUseCase,
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase,
    private val addInterviewUseCase: AddInterviewUseCase,
    private val getInterviewListByCompanyIdUseCase: GetInterviewListByCompanyIdUseCase
) : ViewModel() {

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

    /** Company data **/
    private val _companyList = MutableLiveData<List<CompanyItem>>()
    val companyList: LiveData<List<CompanyItem>> get() = _companyList

    private val _companyItem = MutableLiveData<CompanyItem>()
    val companyItem: LiveData<CompanyItem> get() = _companyItem

    private val _companyName = MutableLiveData<String>()
    val companyName: LiveData<String> = _companyName

    private val _companyPhone = MutableLiveData<String>()
    val companyPhone: LiveData<String> = _companyPhone

    private val _companyEmail = MutableLiveData<String>()
    val companyEmail: LiveData<String> = _companyEmail

    private val _companyContactName = MutableLiveData<String>()
    val companyContactName: LiveData<String> = _companyContactName

    private val _companyContactPhone = MutableLiveData<String>()
    val companyContactPhone: LiveData<String> = _companyContactPhone

    private val _companyContactEmail = MutableLiveData<String>()
    val companyContactEmail: LiveData<String> = _companyContactEmail

    private val _companyDescription = MutableLiveData<String>()
    val companyDescription: LiveData<String> = _companyDescription

    private val _isAddCompanyEnabled = MutableLiveData<Boolean>()
    val isAddCompanyEnabled: LiveData<Boolean> = _isAddCompanyEnabled

    /** Interview data **/
    private val _interviewList = MutableLiveData<List<InterviewItem>>()
    val interviewList: LiveData<List<InterviewItem>> get() = _interviewList

    private val _isAddInterviewEnabled = MutableLiveData<Boolean>()
    val isAddInterviewEnabled: LiveData<Boolean> = _isAddInterviewEnabled

    fun getCompanyList() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(getCompanyUseCase())
        }
    }

    fun getCompanyById(id: Int) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatusById(getCompanyByIdUseCase(id))
        }
    }

    fun getInterviewList(companyId: Int) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatusInterviewByCompanyId(getInterviewListByCompanyIdUseCase(companyId))
        }
    }

    fun onDataChanged(
        companyName: String,
        companyPhone: String,
        companyEmail: String,
        companyContactName: String,
        companyContactPhone: String,
        companyContactEmail: String,
        companyDescription: String
    ) {
        _companyName.value = companyName
        _companyPhone.value = companyPhone
        _companyEmail.value = companyEmail
        _companyContactName.value = companyContactName
        _companyContactPhone.value = companyContactPhone
        _companyContactEmail.value = companyContactEmail
        _companyDescription.value = companyDescription

        _isAddCompanyEnabled.value = companyName.isNotEmpty()
                && companyPhone.isNotEmpty()
                && companyEmail.isNotEmpty()
                && companyEmail.isValidEmail()
                && companyContactName.isNotEmpty()
                && companyContactPhone.isNotEmpty()
                && companyContactEmail.isNotEmpty()
                && companyContactEmail.isValidEmail()
                && companyDescription.isNotEmpty()
    }

    fun addCompanyData(
        companyName: String,
        companyPhone: String,
        companyEmail: String,
        companyContactName: String,
        companyContactPhone: String,
        companyContactEmail: String,
        companyDescription: String
    ) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatusAddResponse(
                addCompanyUseCase(
                    companyName,
                    companyPhone,
                    companyEmail,
                    companyContactName,
                    companyContactPhone,
                    companyContactEmail,
                    companyDescription
                )
            )
        }
    }

    fun addInterviewData(
        companyId: Int,
        date: String,
        hour: String,
        comment: String,
        done: Int
    ) {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatusAddResponse(
                addInterviewUseCase(
                    companyId = companyId,
                    date = date,
                    hour = hour,
                    comment = comment,
                    done = done
                )
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(responseStatus: ApiResponseStatus<List<CompanyItem>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _companyList.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusById(responseStatus: ApiResponseStatus<CompanyItem>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _companyItem.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusAddResponse(responseStatus: ApiResponseStatus<String>) {
        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusInterviewByCompanyId(responseStatus: ApiResponseStatus<List<InterviewItem>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _interviewList.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }
}