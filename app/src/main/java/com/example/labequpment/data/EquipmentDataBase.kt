package com.example.labequpment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Equipment::class], version = 1, exportSchema = false)
abstract class EquipmentDataBase : RoomDatabase() {

    abstract fun getEquipmentDao() : EquipmentDao


    companion object {
        @Volatile
        private var Instance: EquipmentDataBase? = null

        fun getDataBase(context: Context) : EquipmentDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, EquipmentDataBase::class.java, "equipment_database")
                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            }
        }
    }
}