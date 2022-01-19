package com.epam.data

import com.epam.data.local.BatteryLocalDataSource
import com.epam.data.remote.BatteryRemoteDataSource
import com.epam.domain.repository.BatteryRepository

class BatteryRepositoryImpl(private val batteryRemoteDataSource: BatteryRemoteDataSource,
private val batteryLocalDataSource: BatteryLocalDataSource):BatteryRepository {
}