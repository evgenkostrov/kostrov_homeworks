package com.epam.domain.model

data class Song (
    val id : String,
    val title : String,
    val performer : String,
    val coverUrl : String?,
    val year : String,
    val album : String,
    val genre : String,
    val text : String
){
    fun getSongPreview():SongPreview= SongPreview(id, title, performer, coverUrl)
}
