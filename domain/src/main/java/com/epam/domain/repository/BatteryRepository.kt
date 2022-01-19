package com.epam.domain.repository

import com.epam.domain.model.Battery

interface BatteryRepository {
    suspend fun addBatteryFromRepository(battery: Battery)
    suspend fun getBatteryList()
}