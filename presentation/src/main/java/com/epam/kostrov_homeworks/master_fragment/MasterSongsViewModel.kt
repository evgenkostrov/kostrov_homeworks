package com.epam.kostrov_homeworks.master_fragment

import androidx.lifecycle.*
import com.epam.data.repository.MasterSongsRepositoryImpl
import com.epam.domain.model.SongPreview
import com.epam.domain.repository.MasterSongsRepository
import com.epam.domain.usecase.GetMasterSongsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MasterSongsViewModel(
    private var masterSongsRepository: MasterSongsRepository,

    private var getMasterSongsUseCase: GetMasterSongsUseCase) : ViewModel() {

    init {
        masterSongsRepository=MasterSongsRepositoryImpl()
        getMasterSongsUseCase=GetMasterSongsUseCase(masterSongsRepository)
    }

    private val _masterSongs =
        MutableLiveData(getMasterSongsUseCase.invoke())
    val masterSongs: LiveData<List<SongPreview>> = _masterSongs

}

