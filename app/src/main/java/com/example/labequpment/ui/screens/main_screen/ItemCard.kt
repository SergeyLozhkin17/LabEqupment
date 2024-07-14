package com.example.labequpment.ui.screens.main_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labequpment.data.Equipment
import com.example.labequpment.data.MockDb
import com.example.labequpment.ui.theme.LabEquipmentTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EquipmentCard(equipment: Equipment, modifier: Modifier = Modifier) {
    val expanded = remember {
        mutableStateOf(false)
    }
    Log.d("MyTag", "ItemCard recompose")
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                expanded.value = !expanded.value
            }
    ) {
        CardItemBody(
            expanded = expanded.value,
            equipment = equipment,
            onEditItemClick = {},
            onDeleteItemClick = {},
            modifier = modifier
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewEquipmentCard() {

    LabEquipmentTheme {
        EquipmentCard(equipment = MockDb.list[0])
    }
}
