package com.example.labequpment.data

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.GregorianCalendar


@SuppressLint("SimpleDateFormat")
private val sdf = SimpleDateFormat("dd MMMM yyyy")
private val calendar = GregorianCalendar.getInstance()

@Entity(tableName = "equipments")
class Equipment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val verificationPeriodInMonth: Int,
    val factoryNumber: String,
    val name: String,
    val dateOfLastVerification: Long?
) {
    override fun toString(): String {
        return "имя: $name, заводской номер: $factoryNumber, период поверки: $verificationPeriodInMonth, дата: ${
            sdf.format(
                Date(dateOfLastVerification ?: 0)
            )
        }"
    }

    fun getLastVerificationDate(): String {
        val date = Date(dateOfLastVerification ?: throw Exception("Неверная дата"))
        return sdf.format(date)
    }

    fun getNextVerificationDate(): String {
        val calendar = GregorianCalendar.getInstance()
        calendar.time = Date(dateOfLastVerification ?: throw Exception("Неверная дата"))
        calendar.add(Calendar.MONTH, verificationPeriodInMonth)
        return sdf.format(Date(calendar.timeInMillis))
    }

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getRemainingDays(): Long {
        val calendar = GregorianCalendar.getInstance()
        val sdf1 = SimpleDateFormat("yyyy-MM-dd")
        calendar.time = Date(dateOfLastVerification ?: throw Exception())
        calendar.add(Calendar.MONTH, verificationPeriodInMonth)
        Log.d("MyTagTagVerifDate", sdf1.format(Date(calendar.time.time)))
        val today = sdf1.format(Date(Calendar.getInstance().time.time))
        Log.d("MyTagTagtoday", today)
        val parsVerif = LocalDate.parse(sdf1.format(Date(calendar.time.time)))
        val parsToday = LocalDate.parse(today)
        Log.d("MyTagTagRem", ChronoUnit.DAYS.between(parsToday, parsVerif).toString())
        return ChronoUnit.DAYS.between(parsToday, parsVerif)
        /*
                val calendar = GregorianCalendar.getInstance()
                calendar.time = Date(dateOfLastVerification ?: throw Exception("Неверная дата"))
                calendar.add(Calendar.MONTH, verificationPeriodInMonth)
                val nextVerificationDate = sdf1.format(Date(calendar.time.time))
                Log.d("MyTagTagVerifNext", nextVerificationDate)
                val today = sdf1.format(Date(GregorianCalendar.getInstance().time.time))
                Log.d("MyTagTagToday", today)
                val parseVerificationDate = LocalDate.parse(nextVerificationDate)
                val parseToday = LocalDate.parse(today)
                Log.d("MyTagTagRem", ChronoUnit.DAYS.between(parseToday, parseVerificationDate).toString())

                return ChronoUnit.DAYS.between(parseVerificationDate, parseToday)*/
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validateEq(): Boolean {
        return getRemainingDays() > 0
    }
}


