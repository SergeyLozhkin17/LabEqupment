package com.example.labequpment.ui.screens.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.EquipmentRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class MainScreenViewModel(private val equipmentRepository: EquipmentRepository) : ViewModel() {

    private val _mainScreenUiState = mutableStateOf(MainScreenUiState())
    val mainScreenUiState = _mainScreenUiState

    init {
        viewModelScope.launch {
            equipmentRepository.getAllEquipments().collect {
                _mainScreenUiState.value = MainScreenUiState(itemsList = it)
            }
        }
    }
    /*val mainScreenUiState = equipmentRepository.getAllEquipments().map {
        MainScreenUiState(itemsList = it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = MainScreenUiState()
    )*/
    fun sortFromMinToMax() {
        viewModelScope.launch {
            equipmentRepository.getAllEquipments().collect {
                _mainScreenUiState.value = MainScreenUiState(itemsList = it.sortedBy { eq ->
                    eq.getRemainingDays()
                })
            }
        }
    }

    fun sortFromMaxToMin() {
        viewModelScope.launch {
            equipmentRepository.getAllEquipments().collect {
                _mainScreenUiState.value =
                    MainScreenUiState(itemsList = it.sortedByDescending { eq ->
                        eq.getRemainingDays()
                    })
            }
        }
    }

    suspend fun deleteItem(equipment: Equipment) {
        equipmentRepository.deleteItem(equipment)
    }

}

data class MainScreenUiState(
    val searchInput: String = "",
    val itemsList: List<Equipment> = listOf()
)