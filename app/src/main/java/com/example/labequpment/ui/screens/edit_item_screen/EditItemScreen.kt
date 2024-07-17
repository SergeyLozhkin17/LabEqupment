package com.example.labequpment.ui.screens.edit_item_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labequpment.ui.screens.ViewModelsProvider
import com.example.labequpment.ui.screens.add_eqipment_screen.AddEquipmentScreenBody
import com.example.labequpment.ui.screens.navigation.NavigationDestination
import kotlinx.coroutines.launch

object EditItemScreenDestination : NavigationDestination {
    override val route: String
        get() = "Edit Item Screen"
    override val title: String
        get() = "Edit Item Screen"
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditItemScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    editItemScreenViewModel: EditItemScreenViewModel = viewModel(factory = ViewModelsProvider.Factory),
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(modifier = modifier) { innerPadding ->
        AddEquipmentScreenBody(
            itemUiState = editItemScreenViewModel.itemUiState,
            onItemValueChange = editItemScreenViewModel::updateUiState,
            onCancelButtonClick = { navigateUp() },
            onSaveButtonClick = {
                coroutineScope.launch {
                    editItemScreenViewModel.updateItem()
                }
                navigateUp()
            },
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