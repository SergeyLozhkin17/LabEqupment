package com.example.labequpment

import android.app.Application
import com.example.labequpment.data.AppContainer
import com.example.labequpment.data.LocalAppContainer

class EquipmentApplication : Application(){


    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = LocalAppContainer(this)
    }


}