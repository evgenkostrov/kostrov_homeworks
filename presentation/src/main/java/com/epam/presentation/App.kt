package com.epam.presentation

import android.app.Application
import com.epam.data.BatteryRepositoryImpl
import com.epam.data.local.BatteryLocalDataSourceImpl
import com.epam.data.remote.BatteryRemoteDataSourceImpl
import com.epam.domain.repository.BatteryRepository
import com.epam.domain.usecases.AddBatteryUseCase

class App : Application() {

    private val batteryRepository: BatteryRepository
        get() =
            BatteryRepositoryImpl(BatteryRemoteDataSourceImpl(), BatteryLocalDataSourceImpl())

    val addBatteryUseCase: AddBatteryUseCase
        get() = AddBatteryUseCase(batteryRepository)

    override fun onCreate() {
        super.onCreate()


    }

}