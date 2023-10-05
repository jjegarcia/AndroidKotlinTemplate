package com.example.androidkotlintemplate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

interface MainViewModel {
    val screenData: MutableStateFlow<ScreenData>
}

@HiltViewModel
class MainViewModelImpl @Inject constructor() : ViewModel(), MainViewModel {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _photos = MutableLiveData<String>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val photos: LiveData<String> = _photos


    private val _screenData = MutableStateFlow(
        ScreenData(
            "Test",
            ""
        )
    )

    private fun getUrl(thumbnail: Thumbnail): String {
        val string = "${thumbnail.path}/portrait_incredible.${thumbnail.extension}"
        return string
    }

    override val screenData: MutableStateFlow<ScreenData>
        get() = _screenData

    init {
        getCharactersPhotos()
    }

    private fun getCharactersPhotos() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val characters: CharactersResponse = Api.retrofitService.getCharacters()
                val thumbnail = getThumbnail(characters.data.results.get(0).name)
                val url = getUrl(thumbnail)
                screenData.value =
                    screenData.value.copy(url = url)
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
                _status.value = ApiStatus.ERROR
            }
        }
    }

    private suspend fun getThumbnail(name: String): Thumbnail {
        var thumbNail = Thumbnail("", "")
        try {
            val response = Api.retrofitService.getCharacter(name)
            thumbNail = response.data.results.get(0).thumbnail
        } catch (e: Exception) {
            Log.i("Error:", e.toString())
            _status.value = ApiStatus.ERROR
        }
        return thumbNail
    }
}