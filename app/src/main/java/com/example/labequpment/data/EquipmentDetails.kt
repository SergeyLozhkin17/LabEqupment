package com.example.labequpment.data

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.GregorianCalendar



class EquipmentDetails(
    val id : Int,
    val verificationPeriodInMonth: Int,
    val factoryNumber: String,
    val name: String,
    val dateOfLastVerification: Long
) {

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd MMMM yyyy")
    private val date = Date(dateOfLastVerification)
    private val calendar = GregorianCalendar.getInstance()
    fun getLastVerificationDate() : String {
        return sdf.format(date)
    }

    fun getNextVerificationDate() : String {
        calendar.clear()
        calendar.time = Date(dateOfLastVerification)
        calendar.add(Calendar.MONTH, verificationPeriodInMonth)
        return sdf.format(Date(calendar.timeInMillis))
    }
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getRemainingDays() : Long {
        val sdf1 = SimpleDateFormat("yyyy-MM-dd")
        val verificationDate = sdf1.format(Date(calendar.time.time))
        val today = sdf1.format(Date(GregorianCalendar.getInstance().time.time))
        val parseVerificationDate = LocalDate.parse(verificationDate)
        val parseToday = LocalDate.parse(today)
        return ChronoUnit.DAYS.between(parseToday, parseVerificationDate)
    }
}


