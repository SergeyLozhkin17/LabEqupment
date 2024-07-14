package com.example.labequpment.ui.screens.add_eqipment_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.MutableDB

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun AddEquipmentScreen(
    modifier: Modifier = Modifier,
    addItemScreenViewModel: AddItemScreenViewModel = viewModel()
) {
    Log.d("MyTag", "1. Equipment screen recompose")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Entry Item") })
        },
        modifier = modifier
    ) { innerPadding ->
        AddEquipmentScreenBody(
            entryItemUiState = addItemScreenViewModel.entryItemUiState,
            onItemValueChange = addItemScreenViewModel::updateEntryItemUIState,
            onCancelButtonClick = { },
            onSaveButtonClick = { addItemScreenViewModel.saveItem(it) },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }


}

@Composable
private fun AddEquipmentScreenBody(
    entryItemUiState: EntryItemUiState,
    onItemValueChange: (EquipmentDetails) -> Unit,
    onCancelButtonClick: () -> Unit,
    onSaveButtonClick: (Equipment) -> Unit,
    modifier: Modifier = Modifier,
) {
    Log.d("MyTag", "2. body recompose")
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInputForm(
            equipmentDetails = entryItemUiState.equipmentDetails,
            onItemValueChange = onItemValueChange
        )
        VerificationDatePicker(
            equipmentDetails = entryItemUiState.equipmentDetails,
            onItemValueChange = onItemValueChange
        )
        PeriodSlider(
            equipmentDetails = entryItemUiState.equipmentDetails,
            onItemValueChange = onItemValueChange,
            label = "Выберите период поверки: "
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    onCancelButtonClick()
                    MutableDB.db.forEach {
                        Log.d("item", it.toString())
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(65.dp)
                    .padding(8.dp)
            ) {
                Text(text = "Отмена")
            }
            Button(
                onClick = {

                    onSaveButtonClick(entryItemUiState.equipmentDetails.toEquipment())
                },
                modifier = Modifier
                    .weight(1f)
                    .height(65.dp)
                    .padding(8.dp)
            ) {
                Text(text = "Сохранить")
            }
        }
    }
}

@Composable
private fun UserInputForm(
    equipmentDetails: EquipmentDetails,
    onItemValueChange: (EquipmentDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("MyTag", "3. Input form recompose")
    Column(modifier = modifier) {
        OutlinedTextField(
            value = equipmentDetails.name,
            onValueChange = { onItemValueChange(equipmentDetails.copy(name = it)) },
            label = { Text(text = "Название прибора") },
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = equipmentDetails.factoryNumber,
            onValueChange = { onItemValueChange(equipmentDetails.copy(factoryNumber = it)) },
            label = { Text(text = "Заводской №") },
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VerificationDatePicker(
    equipmentDetails: EquipmentDetails,
    onItemValueChange: (EquipmentDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("MyTag", "4. DatePicker recompose")
    val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    DatePicker(state = state, modifier = modifier)
    onItemValueChange(equipmentDetails.copy(dateOfLastVerification = state.selectedDateMillis))
    Log.d("DP", "${equipmentDetails.dateOfLastVerification}")
}


@Composable
private fun PeriodSlider(
    label: String,
    equipmentDetails: EquipmentDetails,
    onItemValueChange: (EquipmentDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("MyTag", "5. Slider recompose")
    Column {
        Text(text = label, modifier = modifier)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(text = "1")
            Text(text = "2")
            Text(text = "3")
        }
    }

    Slider(
        value = equipmentDetails.verificationPeriodInMonth.toFloat(),
        onValueChange = {
            onItemValueChange(equipmentDetails.copy(verificationPeriodInMonth = it.toInt()))
        },
        steps = 1,
        valueRange = 12f..36f
    )
    //Log.d("item", equipmentDetails.toString())
}