package com.example.labequpment.data

import kotlinx.coroutines.flow.Flow

class OfflineEquipmentRepository(private val equipmentDao: EquipmentDao) : EquipmentRepository {
    override fun getAllEquipments(): Flow<List<Equipment>> {
        return equipmentDao.getAllEquipment()
    }

    override fun getItemStream(id: Int): Flow<Equipment?> {
        return equipmentDao.getEquipment(id = id)
    }

    override suspend fun deleteItem(equipment: Equipment) {
        equipmentDao.delete(equipment)
    }

    override suspend fun updateItem(equipment: Equipment) {
        equipmentDao.update(equipment)
    }
}