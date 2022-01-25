package com.epam.kostrov_homeworks.detail_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epam.data.repository.DetailSongRepositoryImpl
import com.epam.domain.model.Song
import com.epam.domain.usecase.GetDetailUseCase


class DetailSongViewModel : ViewModel() {

    private val detailSongRepository = DetailSongRepositoryImpl()
    private val getDetailSongUseCase = GetDetailUseCase(detailSongRepository)

    private val _detailSongs = MutableLiveData<Song>()
    val detailSongs: LiveData<Song> = _detailSongs

    fun getDetailSong(id:String){
        getDetailSongUseCase(id)
        _detailSongs.value=getDetailSongUseCase(id)!!
    }
}
