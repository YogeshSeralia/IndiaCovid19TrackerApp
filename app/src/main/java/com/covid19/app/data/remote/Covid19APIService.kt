package com.covid19.app.data.remote

import com.covid19.app.data.remote.models.*
import retrofit2.http.GET

interface Covid19APIService {
    @GET("raw_data.json")
    suspend fun rawData(): RawData

    @GET("states_daily.json")
    suspend fun dailyChangesOfStates(): DailyStatusData

    @GET("v2/state_district_wise.json")
    suspend fun districtWiseData(): List<DistrictWiseData>

    @GET("data.json")
    suspend fun timeLineData(): TimelineData

    @GET("travel_history.json")
    suspend fun travelData(): TravelData
}