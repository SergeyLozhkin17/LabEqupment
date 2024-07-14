package com.example.labequpment

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.labequpment.ui.screens.LabEquipmentApp
import com.example.labequpment.ui.screens.add_eqipment_screen.AddEquipmentScreen
import com.example.labequpment.ui.screens.main_screen.MainScreen
import com.example.labequpment.ui.theme.LabEquipmentTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                LabEquipmentTheme {
                    AddEquipmentScreen()
                }
            }
    }
}

