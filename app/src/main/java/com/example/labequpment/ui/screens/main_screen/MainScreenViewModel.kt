package com.example.labequpment.ui.screens.main_screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.EquipmentRepository
import com.example.labequpment.data.MockDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainScreenViewModel(private val equipmentRepository: EquipmentRepository) : ViewModel() {

    private val _mainScreenUiState = MutableStateFlow(MainScreenUiState())
    //val mainScreenUiState = _mainScreenUiState.asStateFlow()

    val mainScreenUiState = equipmentRepository.getAllEquipments().map {
        MainScreenUiState(itemsList = it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = MainScreenUiState()
    )

    suspend fun deleteItem(equipment: Equipment) {
        equipmentRepository.deleteItem(equipment)
    }

    init {
        viewModelScope.launch {
            _mainScreenUiState.value = MainScreenUiState(itemsList = MockDb.list)
        }
    }
}

data class MainScreenUiState(
    val searchInput: String = "",
    val itemsList: List<Equipment> = listOf()
)