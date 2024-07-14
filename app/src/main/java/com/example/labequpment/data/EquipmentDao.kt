package com.example.labequpment.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDao {
    @Query("SELECT * FROM equipments ORDER BY name ASC")
    fun getAllEquipment() : Flow<List<Equipment>>
    @Query("SELECT * from equipments WHERE id = :id")
    fun getEquipment(id: Int): Flow<Equipment>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(equipment: Equipment)
    @Update
    suspend fun update(equipment: Equipment)
    @Delete
    suspend fun delete(equipment: Equipment)
}