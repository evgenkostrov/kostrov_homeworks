package com.epam.data.local

import com.epam.domain.model.Battery

interface BatteryLocalDataSource {

    suspend fun addBatteryLocal(battery:Battery)

}