package com.example.labequpment.data

import android.content.Context

interface AppContainer {
    val offlineEquipmentRepository : EquipmentRepository
}

class LocalAppContainer(context: Context) : AppContainer {
    override val offlineEquipmentRepository: EquipmentRepository by lazy {
        OfflineEquipmentRepository(EquipmentDataBase.getDataBase(context).getEquipmentDao())
    }
}