package com.epam.data.local

import com.epam.data.model.Battery

interface BatteryLocalDataSource {
    suspend fun add(battery:Battery)
}