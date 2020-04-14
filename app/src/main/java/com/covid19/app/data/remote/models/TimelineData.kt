package com.covid19.app.data.remote.models

data class TimelineData (
    val cases_time_series: List<Cases_time_series>,
    val key_values_values: List<Key_values>,
    val statewise: List<Statewise>,
    val tested: List<Tested>
)

data class Delta(

    val active: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int
)

data class Cases_time_series(

    val dailyconfirmed: Int,
    val dailydeceased: Int,
    val dailyrecovered: Int,
    val date: String,
    val totalconfirmed: Int,
    val totaldeceased: Int,
    val totalrecovered: Int
)

data class Key_values(

    val confirmeddelta: Int,
    val counterforautotimeupdate: Int,
    val deceaseddelta: Int,
    val lastupdatedtime: String,
    val recovereddelta: Int,
    val statesdelta: Int
)

data class Statewise(

    val active: Int,
    val confirmed: Int,
    val deaths: Int,
    val delta: Delta,
    val deltaconfirmed: Int,
    val deltadeaths: Int,
    val deltarecovered: Int,
    val lastupdatedtime: String,
    val recovered: Int,
    val state: String,
    val statecode: String
)

data class Tested(
    val source: String,
    val testsconductedbyprivatelabs: String,
    val totalindividualstested: String,
    val totalpositivecases: String,
    val totalsamplestested: String,
    val updatetimestamp: String
)