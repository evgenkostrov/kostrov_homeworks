package com.epam.domain.usecases

import com.epam.domain.model.Battery
import com.epam.domain.repository.BatteryRepository

class AddBatteryUseCase(private val batteryRepository:BatteryRepository) {

    suspend fun invoke(battery: Battery) =
       batteryRepository.addBatteryFromRepository(battery)
}