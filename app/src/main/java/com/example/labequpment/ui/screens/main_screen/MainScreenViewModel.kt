package com.example.labequpment.ui.screens.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.MockDb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val _mainScreenUiState = MutableStateFlow(MainScreenUiState())
    val mainScreenUiState = _mainScreenUiState.asStateFlow()

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