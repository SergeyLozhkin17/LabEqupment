package com.example.labequpment.ui.screens.add_eqipment_screen

import android.util.Log
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.labequpment.data.Equipment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationDatePicker(
    equipmentDetails: EquipmentDetails,
    onItemValueChange: (EquipmentDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    DatePicker(state = state, modifier = modifier)
    onItemValueChange(equipmentDetails.copy(dateOfLastVerification = state.selectedDateMillis ?: 0))
    Log.d("DP", "${equipmentDetails.dateOfLastVerification}")
}