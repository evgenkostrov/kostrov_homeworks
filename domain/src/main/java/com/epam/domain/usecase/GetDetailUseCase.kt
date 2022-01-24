package com.epam.domain.usecase

import com.epam.domain.model.Song
import com.epam.domain.repository.DetailSongRepository

class GetDetailUseCase (
    private val detailSongRepository: DetailSongRepository
) {
   fun invoke (id : String) : Song? = detailSongRepository.getSongById(id)
    }

