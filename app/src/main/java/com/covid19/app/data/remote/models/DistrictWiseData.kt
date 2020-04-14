package com.covid19.app.data.remote.models

data class DistrictWiseData  (
    val state : String,
    val districtData : List<DistrictData>
)

data class DistrictData (
    val district : String,
    val confirmed : Int,
    val lastupdatedtime : String,
    val delta : Delta
)