package com.covid19.app.data.remote.models

data class RawData (
    val raw_data : List<PatientData>
)

data class PatientData (
    val agebracket : String,
    val backupnotes : String,
    val contractedfromwhichpatientsuspected : String,
    val currentstatus : String,
    val dateannounced : String,
    val detectedcity : String,
    val detecteddistrict : String,
    val detectedstate : String,
    val estimatedonsetdate : String,
    val gender : String,
    val nationality : String,
    val notes : String,
    val patientnumber : Int,
    val source1 : String,
    val source2 : String,
    val source3 : String,
    val statecode : String,
    val statepatientnumber : String,
    val statuschangedate : String,
    val typeoftransmission : String
)

