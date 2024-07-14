package com.example.labequpment.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.labequpment.ui.screens.add_eqipment_screen.AddEquipmentScreen
import com.example.labequpment.ui.screens.main_screen.MainScreen
import com.example.labequpment.ui.screens.main_screen.MainScreenDestination

@Composable
fun LabEquipmentNaVHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainScreenDestination.route) {
        composable(route = MainScreenDestination.route) {
            MainScreen(navigateToAddScreen = { navController.navigate(AddEquipmentScreen.route) })
        }
        composable(route = AddEquipmentScreen.route) {
            AddEquipmentScreen(navigateBack = { navController.navigateUp() })
        }
    }
}