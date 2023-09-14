package com.albertomier.cv_management.core.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.albertomier.cv_management.core.repository.Resource

class FetchNextSearchPageTask constructor(
    private val query: String,
    private val api: API,
    private val db: DB
) : Runnable {
    private val _liveData = MutableLiveData<Resource<Boolean>>()
    val liveData: LiveData<Resource<Boolean>> = _liveData

    override fun run() {
        TODO("Not yet implemented")
    }
}