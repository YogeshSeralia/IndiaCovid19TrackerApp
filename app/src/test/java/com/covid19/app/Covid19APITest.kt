package com.covid19.app

import com.covid19.app.data.remote.ApiClient
import com.covid19.app.data.remote.models.RawData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class Covid19APITest {

    @Test
    fun `Raw Data API Test`() = runBlocking{
        val rawData: RawData = ApiClient.covid19APIService.rawData()
        for (rawDatum in rawData.raw_data) {
            println("${rawDatum.patientnumber} may be infected from ${rawDatum.contractedfromwhichpatientsuspected}")
        }
        Assert.assertNotNull(rawData)
    }

    @Test
    fun `Timeline API Test`() = runBlocking{
        val rawData = ApiClient.covid19APIService.timeLineData()
        Assert.assertNotNull(rawData)
    }

    @Test
    fun `States Daily Changes API Test`() = runBlocking{
        val rawData = ApiClient.covid19APIService.dailyChangesOfStates()
        Assert.assertNotNull(rawData)
    }

    @Test
    fun `Travel history API Test`() = runBlocking{
        val rawData = ApiClient.covid19APIService.travelData()
        Assert.assertNotNull(rawData)
    }

    @Test
    fun `State-district-wise V2 API Test`() = runBlocking{
        val rawData = ApiClient.covid19APIService.districtWiseData()
        Assert.assertNotNull(rawData)
    }
}