package com.example.labequpment.ui.screens.add_eqipment_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.EquipmentRepository
import java.util.Locale

class AddItemScreenViewModel(private val equipmentRepository: EquipmentRepository) : ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())
        private set


    @RequiresApi(Build.VERSION_CODES.O)
    fun updateEntryItemUIState(equipmentDetails: EquipmentDetails) {
        itemUiState =
            ItemUiState(equipmentDetails = equipmentDetails, validateInput = validateInput())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateInput(): Boolean {
        return itemUiState.equipmentDetails.name.isNotBlank()
                && itemUiState.equipmentDetails.factoryNumber.isNotBlank()
                && itemUiState.equipmentDetails.dateOfLastVerification != null
    }

    suspend fun addItem() {
        equipmentRepository.insertItem(itemUiState.equipmentDetails.toEquipment())
    }
}

data class ItemUiState(
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

fun Equipment.toItemUiState(validateInput: Boolean) = ItemUiState(
    equipmentDetails = this.toEquipmentDetails(),
    validateInput = validateInput
)

fun Equipment.toEquipmentDetails(): EquipmentDetails {
    return EquipmentDetails(
        id = this.id,
        verificationPeriodInMonth = this.verificationPeriodInMonth,
        factoryNumber = this.factoryNumber,
        name = this.name,
        dateOfLastVerification = this.dateOfLastVerification
    )
}