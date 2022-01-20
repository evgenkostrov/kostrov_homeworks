package com.epam.domain.usecase

import com.epam.domain.model.Battery
import com.epam.domain.model.ItemViewModel
import com.epam.domain.repisitory.Repository

class DeleteItemUsecase(private val repository: Repository) {
    operator fun invoke(item: ItemViewModel): List<ItemViewModel> {
        return repository.deleteAdv(item)
    }
}