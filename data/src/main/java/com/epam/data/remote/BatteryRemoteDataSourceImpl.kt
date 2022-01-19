package com.epam.data.remote

import com.epam.domain.model.Battery

class BatteryRemoteDataSourceImpl ():  BatteryRemoteDataSource {

        override suspend fun getBatList(): List<Battery> {
                TODO("Not yet implemented")
        }
}