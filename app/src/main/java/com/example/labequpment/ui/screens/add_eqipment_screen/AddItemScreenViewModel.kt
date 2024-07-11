package com.example.labequpment.ui.screens.add_eqipment_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.labequpment.data.EquipmentDetails

class AddItemScreenViewModel : ViewModel() {
    var entryItemUiState by mutableStateOf(EntryItemUiState())
        private set

    fun updateEntryItemUIState(equipmentDetails: EquipmentDetails) {
        entryItemUiState = EntryItemUiState(equipmentDetails = equipmentDetails)
    }
}

data class EntryItemUiState(
    val equipmentDetails: EquipmentDetails = EquipmentDetails()
)