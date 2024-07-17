package com.example.labequpment.ui.screens.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.labequpment.ui.screens.add_eqipment_screen.AddEquipmentScreen
import com.example.labequpment.ui.screens.edit_item_screen.EditItemScreen
import com.example.labequpment.ui.screens.edit_item_screen.EditItemScreenDestination
import com.example.labequpment.ui.screens.main_screen.MainScreen
import com.example.labequpment.ui.screens.main_screen.MainScreenDestination

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LabEquipmentNaVHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainScreenDestination.route) {
        composable(route = MainScreenDestination.route) {
            MainScreen(
                navigateToAddScreen = { navController.navigate(AddEquipmentScreen.route) },
                navigateToEditScreen = { navController.navigate("${EditItemScreenDestination.route}/${it}") })
        }
        composable(route = AddEquipmentScreen.route) {
            AddEquipmentScreen(navigateBack = { navController.navigateUp() })
        }
        composable(
            route = EditItemScreenDestination.routeWithArgs,
            arguments = listOf(navArgument(EditItemScreenDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            EditItemScreen(navigateUp = { navController.navigateUp() })
        }
    }
}