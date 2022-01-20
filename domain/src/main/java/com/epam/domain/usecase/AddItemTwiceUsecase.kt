package com.epam.domain.usecase

import com.epam.domain.model.Battery
import com.epam.domain.repisitory.Repository

class AddItemTwiceUsecase(private val repository: Repository) {
    operator fun invoke(): Battery {
        return repository.addBattery()
    }
}