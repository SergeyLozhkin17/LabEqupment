package com.example.labequpment.data

import kotlinx.coroutines.flow.Flow

interface EquipmentRepository {

    fun getAllEquipments() : Flow<List<Equipment>>

    fun getItemStream(id: Int) : Flow<Equipment?>

    suspend fun deleteItem(equipment: Equipment)

    suspend fun updateItem(equipment: Equipment)
}