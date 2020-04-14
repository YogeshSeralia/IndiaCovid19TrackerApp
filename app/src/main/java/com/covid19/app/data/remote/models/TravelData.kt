package com.covid19.app.data.remote.models

data class TravelData(
    val travel_history: List<TravelHistory>
)


data class TravelHistory(
    val _cn6ca: Int,
    val accuracylocation: String,
    val address: String,
    val datasource: String,
    val latlong: String,
    val modeoftravel: String,
    val pid: String,
    val placename: String,
    val timefrom: String,
    val timeto: String,
    val type: String
)