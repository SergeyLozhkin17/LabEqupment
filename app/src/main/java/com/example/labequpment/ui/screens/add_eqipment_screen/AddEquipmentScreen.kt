package com.example.labequpment.ui.screens.add_eqipment_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labequpment.ui.theme.LabEquipmentTheme

@Preview(showSystemUi = true)
@Composable
fun AddEquipmentScreen(modifier: Modifier = Modifier) {
    LabEquipmentTheme {
        LazyColumn(modifier = modifier) {
            item {
                AddEquipmentScreenBody(modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun AddEquipmentScreenBody(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Название прибора") },
                shape = MaterialTheme.shapes.medium,
                modifier = modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text(text = "Заводской №") },
                shape = MaterialTheme.shapes.medium,
                modifier = modifier.fillMaxWidth()
            )
            VerificationDatePicker(modifier = modifier)
            Text(text = "Выберите период поверки в годах: ")
            PeriodSlider(modifier = modifier)
            Row(modifier = modifier.fillMaxWidth()) {
                Button(onClick = { /*TODO*/ }, modifier = modifier
                    .weight(1f)
                    .height(48.dp)) {
                    Text(text = "Отмена")
                }
                Button(onClick = { /*TODO*/ }, modifier = modifier
                    .weight(1f)
                    .height(48.dp)) {
                    Text(text = "Сохранить")
                }
            }
        }

    }
}

@Composable
fun PeriodSlider(modifier: Modifier = Modifier) {
    val sliderPosition = remember {
        mutableStateOf(12f)
    }
    val context = LocalContext.current
    Slider(
        value = sliderPosition.value,
        onValueChange = { sliderPosition.value = it },
        steps = 1,
        valueRange = 12f..36f
    )
    Toast.makeText(context, (sliderPosition.value / 12).toInt().toString(), Toast.LENGTH_SHORT).show()
}