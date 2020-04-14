package com.covid19.app

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.covid19.app.data.local.Covid19Database
import com.covid19.app.data.local.tables.patient.Patient
import com.covid19.app.data.local.tables.patient.PatientDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RoomDBTest {

    private lateinit var covid19Database: Covid19Database
    private lateinit var patientDao: PatientDao

    private val patient1 = Patient(
        1,
        "20",
        "Student from Wuhan",
        "",
        "Recovered",
        "30/01/2020",
        "Thrissur",
        "Thrissur",
        "Kerala",
        "",
        "F",
        "India",
        "Travelled from Wuhan",
        "https://twitter.com/vijayanpinarayi/status/1222819465143832577",
        "https://weather.com/en-IN/india/news/news/2020-02-14-kerala-defeats-coronavirus-indias-three-covid-19-patients-successfully",
        "",
        "KL",
        "KL-TS-P1",
        "14/02/2020",
        "Imported"
    )

    private val patient2 = Patient(
        2,
        "",
        "Student from Wuhan",
        "",
        "Recovered",
        "02/02/2020",
        "Alappuzha",
        "Alappuzha",
        "Kerala",
        "",
        "",
        "India",
        "Travelled from Wuhan",
        "https://www.indiatoday.in/india/story/kerala-reports-second-case-of-coronavirus-1642494-2020-02-02",
        "https://weather.com/en-IN/india/news/news/2020-02-14-kerala-defeats-coronavirus-indias-three-covid-19-patients-successfully",
        "",
        "KL",
        "KL-AL-P1",
        "14/02/2020",
        "Imported"
    )

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        covid19Database =
            Room.inMemoryDatabaseBuilder(appContext, Covid19Database::class.java).build()
        patientDao = covid19Database.patientDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        covid19Database.close()
    }

    @Test
    @Throws(Exception::class)
    fun patientTableInsertTest() {
        patientDao.insertAll(patient1, patient2)
        Assert.assertEquals(
            "Saved rows should be equal ",
            2,
            patientDao.getAll().size
        )
    }

    @Test
    @Throws(Exception::class)
    fun patientTableSelectAllTest() {
        val all = patientDao.getAll()
        println("total patients = ${all.size}")
        Assert.assertNotNull(all)
    }
}