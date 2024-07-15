package com.example.labequpment.ui.screens.add_eqipment_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.EquipmentRepository
import java.util.Locale

class AddItemScreenViewModel(private val equipmentRepository: EquipmentRepository) : ViewModel() {
    var entryItemUiState by mutableStateOf(EntryItemUiState())
        private set


    @RequiresApi(Build.VERSION_CODES.O)
    fun updateEntryItemUIState(equipmentDetails: EquipmentDetails) {
        entryItemUiState =
            EntryItemUiState(equipmentDetails = equipmentDetails, validateInput = validateInput())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateInput(): Boolean {
        return entryItemUiState.equipmentDetails.name.isNotBlank()
                && entryItemUiState.equipmentDetails.factoryNumber.isNotBlank()
                && entryItemUiState.equipmentDetails.dateOfLastVerification != null
    }

    suspend fun addItem() {
        equipmentRepository.insertItem(entryItemUiState.equipmentDetails.toEquipment())
    }
}

data class EntryItemUiState(
    val equipmentDetails: EquipmentDetails = EquipmentDetails(),
    val validateInput: Boolean = false
)

data class EquipmentDetails(
    val id: Int = 0,
    val verificationPeriodInMonth: Int = 12,
    val factoryNumber: String = "",
    val name: String = "",
    val dateOfLastVerification: Long? = null
)

fun EquipmentDetails.toEquipment() = Equipment(
    id = this.id,
    name = this.name,
    factoryNumber = this.factoryNumber.uppercase(Locale.ROOT),
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