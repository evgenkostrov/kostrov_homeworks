package com.epam.domain.usecase

import com.epam.domain.model.SongPreview
import com.epam.domain.repository.MasterSongsRepository

class GetMasterSongsUseCase(
    private val masterSongsRepository: MasterSongsRepository
) {
    fun invoke(): List<SongPreview> = masterSongsRepository.getSongPreviewList()
}
