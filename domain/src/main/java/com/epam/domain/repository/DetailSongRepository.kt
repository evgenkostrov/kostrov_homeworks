package com.epam.domain.repository

import com.epam.domain.model.Song

interface DetailSongRepository {
    fun getSongById(id : String) : Song?
}