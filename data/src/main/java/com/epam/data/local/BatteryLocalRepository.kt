package com.epam.data.local

import com.epam.data.model.Battery

class BatteryLocalRepository (private val batteryLocalDataSource: BatteryLocalDataSource) {
suspend fun addBattery(battery: Battery)=batteryLocalDataSource.add(battery)
}