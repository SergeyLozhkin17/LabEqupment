package com.example.labequpment.ui.screens.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labequpment.data.Equipment


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardItemBody(
    equipment: Equipment,
    expanded: Boolean,
    onEditItemClick: () -> Unit,
    onDeleteItemClick: (Equipment) -> Unit,
    modifier: Modifier = Modifier,
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
                text = equipment.name,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Заводской номер: ${equipment.factoryNumber}",
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
                    text = equipment.getLastVerificationDate(),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Дата следующей поверки: ",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = equipment.getNextVerificationDate(),
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
                text = "${equipment.getRemainingDays()}",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    )
    {
        if (expanded)
            TabRow(
                selectedTabIndex = 2,
                containerColor = Color.Black.copy(alpha = 0.0f),
                modifier = Modifier.padding(8.dp)
            ) {
                val openAlertDialogState = remember {
                    mutableStateOf(false)
                }
                Tab(selected = false, onClick = onEditItemClick) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
                        Text(text = "Изменить")
                    }
                }
                Tab(
                    selected = false,
                    onClick = { openAlertDialogState.value = true }) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
                        Text(text = "Удалить")
                    }
                }
                if (openAlertDialogState.value) {
                    AlertDialog(
                        onDismissRequest = {
                            openAlertDialogState.value = !openAlertDialogState.value
                        },
                        confirmButton = {
                            Button(
                                onClick = { onDeleteItemClick(equipment) },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Да")
                            }
                        },
                        dismissButton = {
                            Button(onClick = {
                                openAlertDialogState.value = !openAlertDialogState.value
                            }, modifier = Modifier.weight(1f)) {
                                Text(text = "Нет")
                            }
                        },
                        title = { Text(text = "Подтвердить удаление?") })
                }
            }
    }
}

