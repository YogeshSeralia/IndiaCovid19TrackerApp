package com.covid19.app.data.remote.models

data class DailyStatusData(
    val states_daily : List<StatesDaily>
)

data class StatesDaily (

    val andamanandnicobarislands : Int,
    val andhrapradesh : Int,
    val arunachalpradesh : Int,
    val assam : Int,
    val bihar : Int,
    val chandigarh : Int,
    val chhattisgarh : Int,
    val dadranagarhave : Int,
    val damananddiu : Int,
    val delhi : Int,
    val goa : Int,
    val gujarat : Int,
    val haryana : Int,
    val himachalpradesh : Int,
    val jammuandkashmir : Int,
    val jharkhand : Int,
    val karnataka : Int,
    val kerala : Int,
    val ladakh : Int,
    val lakshadweep : Int,
    val madhyapradesh : Int,
    val maharashtra : Int,
    val manipur : Int,
    val meghalaya : Int,
    val mizoram : Int,
    val nagaland : Int,
    val odisha : Int,
    val puducherry : Int,
    val punjab : Int,
    val rajasthan : Int,
    val sikkim : Int,
    val tamilnadu : Int,
    val telangana : Int,
    val tripura : Int,
    val uttarakhand : Int,
    val uttarpradesh : Int,
    val westbengal : Int,

    val date : String,
    val status : String,
    val total : Int

)