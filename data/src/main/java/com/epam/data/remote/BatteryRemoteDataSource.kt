package com.epam.data.remote

import com.epam.data.model.Battery

interface BatteryRemoteDataSource {
    suspend fun getBatList(): List<Battery>
}