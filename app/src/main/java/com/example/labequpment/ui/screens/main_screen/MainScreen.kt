package com.example.labequpment.ui.screens.main_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labequpment.data.Equipment
import com.example.labequpment.ui.theme.LabEquipmentTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    MainScreenBody(
        list = mainScreenViewModel.dataBase,
        mainScreenUiState = mainScreenViewModel.mainScreenUistate,
        onSearchValueChange = mainScreenViewModel::updateMainScreenUiState,
        modifier = modifier.padding(8.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenBody(
    mainScreenUiState: MainScreenUiState,
    onSearchValueChange: (String) -> Unit,
    list: List<Equipment>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = mainScreenUiState.searchInput,
            onValueChange = onSearchValueChange,
            shape = MaterialTheme.shapes.medium,
            placeholder = { Text(text = "Поиск") },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        LazyColumn(modifier = Modifier) {
            item {
                list.forEach {
                    EquipmentCard(
                        equipment = it,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewMainScreen() {
    LabEquipmentTheme {
        MainScreen()
    }
}
