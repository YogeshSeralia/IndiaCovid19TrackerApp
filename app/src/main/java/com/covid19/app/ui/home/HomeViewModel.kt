package com.covid19.app.ui.home

import androidx.lifecycle.ViewModel
import com.covid19.app.data.remote.models.DistrictWiseData
import com.covid19.app.data.shared.Result
import com.covid19.app.data.shared.Result.Success
import com.covid19.app.data.shared.StateWiseDataRepo
import com.covid19.app.ui.home.list.DistrictListDataMapper
import com.covid19.app.ui.home.list.ListData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class HomeViewModel : ViewModel(), CoroutineScope {

    private val parentJob: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    private val stateWiseDataRepo: StateWiseDataRepo

    init {
        stateWiseDataRepo = StateWiseDataRepo()
    }

    val stateWiseData: Flow<Result<Any>> = stateWiseDataRepo.fetchDistrictWiseData().map {
        if (it is Success) {
            val data: List<ListData> = DistrictListDataMapper.jsonToList(it.data as List<DistrictWiseData>)
            Success(data)
        } else it
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}