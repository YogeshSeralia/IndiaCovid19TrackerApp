package com.covid19.app.ui.home.list

import com.covid19.app.data.remote.models.DistrictWiseData
import java.util.*

object DistrictListDataMapper {

    fun jsonToList(list: List<DistrictWiseData>): List<ListData> {
        val result = ArrayList<ListData>()
        for (data in list) {
            val districtList = ArrayList<ListData>()
            var stateCount = 0
            for (district in data.districtData) {
                stateCount += district.confirmed
                districtList.add(
                    ListData(
                        data.state,
                        district.district,
                        ListDataType.DISTRICT,
                        district.confirmed,
                        district.lastupdatedtime
                    )
                )
                districtList.sortWith(compareBy({it.confirmed}))
            }
            val state = ListData(data.state, data.state, ListDataType.STATE_HEADER, stateCount)
            result.add(state)
            result.addAll(districtList)
        }

        return result
    }
}

data class ListData(
    val stateName: String,
    val name: String,
    val type: Int,
    val confirmed: Int = 0,
    val delta: String = "",
    val lastUpdatedTime: String = ""
)


object ListDataType {
    val STATE_HEADER = 1
    val DISTRICT = 2
}
