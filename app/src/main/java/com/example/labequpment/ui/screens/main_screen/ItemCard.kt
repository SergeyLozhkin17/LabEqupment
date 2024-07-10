package com.example.labequpment.ui.screens.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labequpment.data.EquipmentDetails
import com.example.labequpment.ui.theme.LabEquipmentTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EquipmentCard(equipmentDetails: EquipmentDetails, modifier: Modifier = Modifier) {

    val expanded = remember {
        mutableStateOf(false)
    }
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
            .animateContentSize(animationSpec = spring(dampingRatio = 0.8f)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = equipmentDetails.name,
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Заводской номер: ${equipmentDetails.factoryNumber}",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Дата поверки: ",
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Text(
                        text = equipmentDetails.getLastVerificationDate(),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Дата следующей поверки: ",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = equipmentDetails.getNextVerificationDate(),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "дней до поверки",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "${equipmentDetails.getRemainingDays()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            if (expanded.value)
                TabRow(selectedTabIndex = 2, containerColor = Color.Black.copy(alpha = 0.0f), modifier = Modifier.padding(8.dp)) {
                    Tab(selected = false, onClick = { /*TODO*/ }) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
                            Text(text = "Изменить")
                        }
                    }
                    Tab(selected = false, onClick = { /*TODO*/ }) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                            Text(text = "Удалить")
                        }
                    }
                }

        }
    }
}

private enum class TabRowElements {
    Edit, Delete
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewEquipmentCard() {
    val testEq = EquipmentDetails(
        id = 1,
        name = "ДАГ-500",
        factoryNumber = "2B110A24",
        dateOfLastVerification = 1719881150119L,
        verificationPeriodInMonth = 12
    )
    LabEquipmentTheme {
        EquipmentCard(equipmentDetails = testEq)
    }
}
