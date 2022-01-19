package com.epam.data.remote

import com.epam.domain.model.Battery

interface BatteryRemoteDataSource {
    suspend fun getBatList(): List<Battery>
}