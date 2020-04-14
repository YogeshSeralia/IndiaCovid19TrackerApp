package com.covid19.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.covid19.app.data.local.tables.patient.Patient
import com.covid19.app.data.local.tables.patient.PatientDao

@Database(entities = [Patient::class], version = 1, exportSchema = false)
abstract class Covid19Database : RoomDatabase() {
    abstract fun patientDao(): PatientDao
}