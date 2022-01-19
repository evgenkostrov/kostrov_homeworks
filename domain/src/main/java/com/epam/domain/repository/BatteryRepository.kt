package com.epam.domain.repository

interface BatteryRepository{
    suspend fun add()
    suspend fun getBatteryList()
}