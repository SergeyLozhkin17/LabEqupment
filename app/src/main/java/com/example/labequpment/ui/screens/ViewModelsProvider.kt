package com.example.labequpment.ui.screens

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.labequpment.EquipmentApplication
import com.example.labequpment.ui.screens.add_eqipment_screen.AddItemScreenViewModel
import com.example.labequpment.ui.screens.main_screen.MainScreenViewModel

object ViewModelsProvider {

    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            MainScreenViewModel(equipmentApplication().container.offlineEquipmentRepository)
        }
        // Initializer for ItemEntryViewModel
        initializer {
            AddItemScreenViewModel(equipmentApplication().container.offlineEquipmentRepository)
        }
    }

}

fun CreationExtras.equipmentApplication(): EquipmentApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as EquipmentApplication)
