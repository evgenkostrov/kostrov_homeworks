package com.epam.kostrov_homeworks.detail_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.epam.domain.usecase.GetDetailUseCase
import com.epam.kostrov_homeworks.R


class DetailSongViewModel (
    application: Application,
    private val getSongUseCase: GetDetailUseCase
) : AndroidViewModel(application) {

    private val _title = MutableLiveData<String>()
    val title : LiveData<String> = _title

    private val _performer = MutableLiveData<String>()
    val performer : LiveData<String> = _performer

    private val _text = MutableLiveData<String>()
    val text : LiveData<String> = _text

    private val _url = MutableLiveData<String>()
    val url : LiveData<String> = _url


    private val _albumInDialog = MutableLiveData<String>()
    val albumInDialog : LiveData<String> = _albumInDialog

    private val _yearInDialog = MutableLiveData<String>()
    val yearInDialog : LiveData<String> = _yearInDialog

    private val _genreInDialog = MutableLiveData<String>()
    val genreInDialog : LiveData<String> = _genreInDialog

    fun onArgumentsReceived(id: String?) {
        if(id != null) {
            val song = getSongUseCase.invoke(id)

            _title.value = song?.title
            _performer.value = song?.performer
            _text.value = song?.text
            _albumInDialog.value = String.format(getApplication<Application>().resources.getString(R.string.song_dialog_album), song?.album)
            _yearInDialog.value = String.format(getApplication<Application>().resources.getString(R.string.song_dialog_year), song?.year)
            _genreInDialog.value = String.format(getApplication<Application>().resources.getString(R.string.song_dialog_genre), song?.genre)
        }
    }

}
