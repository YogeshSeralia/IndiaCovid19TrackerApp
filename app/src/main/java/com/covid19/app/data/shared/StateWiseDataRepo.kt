package com.covid19.app.data.shared

import com.covid19.app.data.local.Covid19Database
import com.covid19.app.data.remote.ApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StateWiseDataRepo {
    fun fetchDistrictWiseData():Flow<Result<Any>> = flow {
        runCatching {
            emit(Result.Loading(true))
            val data = ApiClient.covid19APIService.districtWiseData()
            emit(Result.Success(data))
            emit(Result.Loading(false))
        }.onFailure {
            emit(Result.Error(throwable = it))
            emit(Result.Loading(false))
        }
    }
}