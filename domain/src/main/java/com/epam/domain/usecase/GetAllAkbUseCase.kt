package com.epam.domain.usecase

import com.epam.domain.model.ItemViewModel
import com.epam.domain.repisitory.Repository

class GetAllAkbUseCase(private val repository: Repository) {
    operator fun invoke(): List<ItemViewModel> {
        return repository.getAllAKB()
    }
}