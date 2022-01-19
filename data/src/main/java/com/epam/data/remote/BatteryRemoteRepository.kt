package com.epam.data.remote

import com.epam.data.local.BatteryLocalDataSource
import com.epam.data.model.Battery

class BatteryRemoteRepository (private val batteryRemoteDataSource: BatteryRemoteDataSource) {
        suspend fun addBattery()=batteryRemoteDataSource.getBatList()
}