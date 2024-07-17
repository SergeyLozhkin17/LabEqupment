package com.example.labequpment.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.labequpment.EquipmentApplication
import com.example.labequpment.ui.screens.add_eqipment_screen.AddItemScreenViewModel
import com.example.labequpment.ui.screens.edit_item_screen.EditItemScreenViewModel
import com.example.labequpment.ui.screens.main_screen.MainScreenViewModel

object ViewModelsProvider {

    @RequiresApi(Build.VERSION_CODES.O)
    val Factory = viewModelFactory {
        initializer {
            MainScreenViewModel(equipmentApplication().container.offlineEquipmentRepository)
        }
        initializer {
            AddItemScreenViewModel(equipmentApplication().container.offlineEquipmentRepository)
        }
        initializer {
            EditItemScreenViewModel(
                this.createSavedStateHandle(),
                equipmentApplication().container.offlineEquipmentRepository
            )
        }
    }

}

fun CreationExtras.equipmentApplication(): EquipmentApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as EquipmentApplication)
