package com.albertomier.cv_management.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.cv_management.R
import com.albertomier.cv_management.company.domain.GetCompanyByIdUseCase
import com.albertomier.cv_management.company.domain.GetCompanyUseCase
import com.albertomier.cv_management.company.domain.model.CompanyItem
import com.albertomier.cv_management.core.network.ApiResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCompanyUseCase: GetCompanyUseCase,
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase
) : ViewModel() {

    private val _companyList = MutableLiveData<List<CompanyItem>>()
    val companyList: LiveData<List<CompanyItem>> get() = _companyList

    private val _companyItem = MutableLiveData<CompanyItem>()
    val companyItem: LiveData<CompanyItem> get() = _companyItem

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

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

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(responseStatus: ApiResponseStatus<List<CompanyItem>>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _companyList.value = responseStatus.data
        }

        _status.value = responseStatus as ApiResponseStatus<Any>
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatusById(responseStatus: ApiResponseStatus<CompanyItem?>) {
        if (responseStatus is ApiResponseStatus.Success) {
            _companyItem.value = responseStatus.data
        }

        if (_companyItem.value == null) {
            _status.value = ApiResponseStatus.Error(R.string.error_data)
        } else {
            _status.value = responseStatus as ApiResponseStatus<Any>
        }
    }
}