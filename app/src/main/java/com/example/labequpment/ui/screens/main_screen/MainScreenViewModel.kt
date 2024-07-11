package com.example.labequpment.ui.screens.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.labequpment.data.MockDb

class MainScreenViewModel : ViewModel() {

    val dataBase = MockDb.list
    var mainScreenUistate by mutableStateOf(MainScreenUiState())
        private set

    fun updateMainScreenUiState(input: String) {
        mainScreenUistate = MainScreenUiState().copy(searchInput = input)
    }
}

data class MainScreenUiState(
    val searchInput: String = ""
)