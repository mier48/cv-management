package com.albertomier.cv_management.core.network

import com.albertomier.cv_management.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException

suspend fun <T> makeNetworkCall(
    call: suspend () -> T
): ApiResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ApiResponseStatus.Success(call())
    } catch (e: UnknownHostException) {
        ApiResponseStatus.Error(R.string.unknow_host_exception_error)
    } catch (e: HttpException) {
        ApiResponseStatus.Error(R.string.unknow_error)
    } catch (e: Exception) {
        e.printStackTrace()
        ApiResponseStatus.Error(R.string.unknow_error)
    }
}