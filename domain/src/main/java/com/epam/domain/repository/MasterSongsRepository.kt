package com.epam.domain.repository

import com.epam.domain.model.SongPreview

interface MasterSongsRepository {
    fun getSongPreviewList() : List<SongPreview>
}