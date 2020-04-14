package com.covid19.app.data.local.tables.patient

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PatientDao {

    @Query("SELECT * FROM Patient")
    fun getAll(): List<Patient>

    @Query("SELECT * FROM patient WHERE patientNumber IN (:patientIds)")
    fun loadAllByPatientNumbers(patientIds: IntArray): List<Patient>

    @Insert
    fun insertAll(vararg users: Patient)
}