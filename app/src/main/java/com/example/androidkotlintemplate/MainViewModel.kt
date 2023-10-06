package com.example.androidkotlintemplate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }
data class CharacterInfo(
    val url: String = "",
    val description: String = ""
)

interface MainViewModel {
    val screenData: MutableStateFlow<ScreenData>
}

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val api: ApiService
) : ViewModel(), MainViewModel, ApiService by api {

    private val _status = MutableLiveData<ApiStatus>()

    private val _screenData = MutableStateFlow(ScreenData())

    private fun getUrl(thumbnail: Thumbnail): String =
        "${thumbnail.path}/portrait_incredible.${thumbnail.extension}"

    override val screenData: MutableStateFlow<ScreenData>
        get() = _screenData

    init {
        getCharactersPhotos()
    }

    private fun getCharactersPhotos() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val characters: List<CharacterInfo> =
                    api.getCharacters().data.results.map { getCharacterInfo(it.name) }
                screenData.value = screenData.value.copy(characters = characters)
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
                _status.value = ApiStatus.ERROR
            }
        }
    }

    private suspend fun getCharacterInfo(name: String): CharacterInfo {
        try {
            val character = api.getCharacter(name)//retrofitService.getCharacter(name)
            return CharacterInfo(
                url = getUrl(character.data.results[0].thumbnail),
                description = character.data.results[0].description
            )
        } catch (e: Exception) {
            Log.i("Error:", e.toString())
            _status.value = ApiStatus.ERROR
        }
        return CharacterInfo()
    }
}