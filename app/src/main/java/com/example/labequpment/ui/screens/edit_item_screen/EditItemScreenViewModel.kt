package com.example.labequpment.ui.screens.edit_item_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labequpment.data.EquipmentRepository
import com.example.labequpment.ui.screens.add_eqipment_screen.EquipmentDetails
import com.example.labequpment.ui.screens.add_eqipment_screen.ItemUiState
import com.example.labequpment.ui.screens.add_eqipment_screen.toEquipment
import com.example.labequpment.ui.screens.add_eqipment_screen.toEquipmentDetails
import com.example.labequpment.ui.screens.add_eqipment_screen.toItemUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditItemScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val equipmentRepository: EquipmentRepository
) : ViewModel() {
    var itemUiState by mutableStateOf(ItemUiState())

    val id: Int = savedStateHandle[EditItemScreenDestination.itemIdArg] ?: 0

    init {
        viewModelScope.launch {
            itemUiState =
                equipmentRepository.getItemStream(id).filterNotNull().first().toItemUiState(true)
        }
    }

    suspend fun updateItem() {
        if (validateInput(uiState = itemUiState.equipmentDetails)) {
            equipmentRepository.updateItem(itemUiState.equipmentDetails.toEquipment())
        }
    }

    fun updateUiState(equipmentDetails: EquipmentDetails) {
        itemUiState = ItemUiState(
            equipmentDetails = equipmentDetails,
            validateInput = validateInput(equipmentDetails)
        )
    }

    private fun validateInput(uiState: EquipmentDetails = itemUiState.equipmentDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && factoryNumber.isNotBlank() && dateOfLastVerification != null
        }
    }
}