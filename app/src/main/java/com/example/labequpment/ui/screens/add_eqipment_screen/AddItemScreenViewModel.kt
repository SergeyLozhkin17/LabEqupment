package com.example.labequpment.ui.screens.add_eqipment_screen

import androidx.compose.runtime.FloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.MutableDB

class AddItemScreenViewModel : ViewModel() {
    var entryItemUiState by mutableStateOf(EntryItemUiState())
        private set
    fun updateEntryItemUIState(equipmentDetails: EquipmentDetails) {
        entryItemUiState = EntryItemUiState(equipmentDetails = equipmentDetails)
    }
    fun saveItem(equipment: Equipment) {
        MutableDB.db.add(equipment)
    }
}

data class EntryItemUiState(
    val equipmentDetails: EquipmentDetails = EquipmentDetails(),
)

data class EquipmentDetails(
    val id : Int = 0,
    val verificationPeriodInMonth: Int = 12,
    val factoryNumber: String = "",
    val name: String = "",
    val dateOfLastVerification: Long? = null
)

fun EquipmentDetails.toEquipment() = Equipment(
    id = this.id,
    name = this.name,
    factoryNumber = this.factoryNumber,
    verificationPeriodInMonth = this.verificationPeriodInMonth,
    dateOfLastVerification = this.dateOfLastVerification
)

fun Equipment.toEquipmentDetails() = EquipmentDetails(
    id = this.id,
    name = this.name,
    factoryNumber = this.factoryNumber,
    verificationPeriodInMonth = this.verificationPeriodInMonth,
    dateOfLastVerification = this.dateOfLastVerification
)