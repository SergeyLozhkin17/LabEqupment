package com.example.labequpment.ui.screens.main_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labequpment.data.Equipment
import com.example.labequpment.ui.screens.ViewModelsProvider
import com.example.labequpment.ui.screens.navigation.NavigationDestination
import com.example.labequpment.ui.theme.LabEquipmentTheme
import kotlinx.coroutines.launch

object MainScreenDestination : NavigationDestination {
    override val route: String = "Main Screen"
    override val title: String = "Lab Equipment App"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navigateToAddScreen: () -> Unit,
    modifier: Modifier = Modifier,
    navigateToEditScreen : (Int) -> Unit,
    mainScreenViewModel: MainScreenViewModel = viewModel(factory = ViewModelsProvider.Factory)
) {
    val mainScreenUiState by mainScreenViewModel.mainScreenUiState
    //val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()
    Log.d("MyTag", "MainScreen recompose")
    Scaffold(
        modifier = modifier,
        topBar = {
            EquipmentSearchBar(
                mainScreenUiState = mainScreenUiState,
                onSearchValueChange = {},
                sortItemsMaxToMin = mainScreenViewModel::sortFromMinToMax,
                sortItemsMixToMax = mainScreenViewModel::sortFromMaxToMin,
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddScreen,
                modifier = Modifier.size(80.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        MainScreenBody(
            onEditItemClick = navigateToEditScreen,
            onDeleteItemClick = {
                coroutineScope.launch {
                    mainScreenViewModel.deleteItem(it)
                }
            },
            itemsList = mainScreenUiState.itemsList,
            onItemClick = { /*TODO*/ },
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipmentSearchBar(
    mainScreenUiState: MainScreenUiState,
    onSearchValueChange: (String) -> Unit,
    sortItemsMaxToMin: () -> Unit,
    sortItemsMixToMax: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var sortstate by remember {
        mutableStateOf(false)
    }

    SearchBar(
        query = mainScreenUiState.searchInput,
        onQueryChange = onSearchValueChange,
        onSearch = {
            expanded = false
        },
        active = expanded,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                if (sortstate) {
                    sortItemsMixToMax()
                    sortstate = !sortstate
                } else {
                    sortItemsMaxToMin()
                    sortstate = !sortstate
                }
            }) {
                if (sortstate) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)

                } else {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
                }
            }
        },
        onActiveChange = { expanded = it },
        placeholder = { Text(text = "Search bar") },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        LazyColumn {
            items(
                items = mainScreenUiState.itemsList.distinctBy { it.name },
                key = { it.id }) {
                ListItem(
                    headlineContent = { Text(text = it.name) },
                    modifier = Modifier.clickable {
                        onSearchValueChange(it.name)
                        expanded = false
                    })
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MainScreenBody(
    itemsList: List<Equipment>,
    onEditItemClick: (Int) -> Unit,
    onDeleteItemClick: (Equipment) -> Unit,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("MyTag", "MainScreenBody recompose")
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (itemsList.isEmpty()) {
            Text(
                text = "Список приборов пуст нажмите '+' что бы добавить",
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }
        EquipmentList(
            itemsList = itemsList,
            onItemClick = onItemClick,
            onDeleteItemClick = onDeleteItemClick,
            onEditItemClick = onEditItemClick
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun EquipmentList(
    itemsList: List<Equipment>,
    onItemClick: () -> Unit,
    onEditItemClick: (Int) -> Unit,
    onDeleteItemClick: (Equipment) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("MyTag", "ItemScreenList recompose")
    LazyColumn(modifier = modifier.fillMaxHeight()) {
        items(items = itemsList, key = { it.id }) { equipment ->
            EquipmentCard(
                onEditItemClick = { onEditItemClick(equipment.id) },
                onDeleteItemClick = onDeleteItemClick,
                equipment = equipment,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onItemClick()
                    }
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun PreviewMainScreen() {
    LabEquipmentTheme {
        MainScreen(navigateToAddScreen = {}, navigateToEditScreen = {})
    }
}
