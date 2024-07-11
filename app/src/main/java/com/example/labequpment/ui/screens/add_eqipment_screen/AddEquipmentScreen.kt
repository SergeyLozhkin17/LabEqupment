package com.example.labequpment.ui.screens.add_eqipment_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.MutableDB
import com.example.labequpment.ui.theme.LabEquipmentTheme

@Preview(showSystemUi = true)
@Composable
fun AddEquipmentScreen(
    modifier: Modifier = Modifier,
    addItemScreenViewModel: AddItemScreenViewModel = viewModel()
) {
    LabEquipmentTheme {
        LazyColumn(modifier = modifier) {
            item {
                AddEquipmentScreenBody(
                    equipmentDetails = addItemScreenViewModel.entryItemUiState.equipmentDetails,
                    onItemValueChange = addItemScreenViewModel::updateEntryItemUIState,
                    onCancelButtonClick = { },
                    onSaveButtonClick = { addItemScreenViewModel.saveItem(it) },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun AddEquipmentScreenBody(
    equipmentDetails: EquipmentDetails,
    onItemValueChange: (EquipmentDetails) -> Unit,
    onCancelButtonClick: () -> Unit,
    onSaveButtonClick: (Equipment) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier) {
            OutlinedTextField(
                value = equipmentDetails.name,
                onValueChange = { onItemValueChange(equipmentDetails.copy(name = it)) },
                label = { Text(text = "Название прибора") },
                shape = MaterialTheme.shapes.medium,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                modifier = modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = equipmentDetails.factoryNumber,
                onValueChange = { onItemValueChange(equipmentDetails.copy(factoryNumber = it)) },
                label = { Text(text = "Заводской №") },
                shape = MaterialTheme.shapes.medium,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                modifier = modifier.fillMaxWidth()
            )
            VerificationDatePicker(
                equipmentDetails = equipmentDetails,
                onItemValueChange = onItemValueChange
            )
            PeriodSlider(
                equipmentDetails = equipmentDetails,
                onItemValueChange = onItemValueChange,
                modifier = modifier
            )
            Row(modifier = modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        onCancelButtonClick()
                        MutableDB.db.forEach {
                            Log.d("item", it.toString())
                        }
                    },
                    modifier = modifier
                        .weight(1f)
                        .height(48.dp)
                ) {
                    Text(text = "Отмена")
                }
                Button(
                    onClick = {
                        onSaveButtonClick(equipmentDetails.toEquipment())
                    },
                    modifier = modifier
                        .weight(1f)
                        .height(48.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Сохранить")
                }
            }
        }

    }
}

@Composable
fun PeriodSlider(
    equipmentDetails: EquipmentDetails,
    onItemValueChange: (EquipmentDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = "Выберите период поверки в годах: ", modifier = modifier)
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