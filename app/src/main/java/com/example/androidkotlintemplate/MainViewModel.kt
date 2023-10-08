package com.example.androidkotlintemplate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidkotlintemplate.database.DatabaseCharacterInfo
import com.example.androidkotlintemplate.network.ApiService
import com.example.androidkotlintemplate.network.CharactersRepository
import com.example.androidkotlintemplate.network.Thumbnail
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
    private val apiService: ApiService,
    private val charactersRepository: CharactersRepository
) : ViewModel(), MainViewModel, ApiService by apiService {

    private val _status = MutableLiveData<ApiStatus>()
    private val _screenData = MutableStateFlow(ScreenData())

    private fun getUrl(thumbnail: Thumbnail): String =
        "${thumbnail.path}/portrait_incredible.${thumbnail.extension}"

    override val screenData: MutableStateFlow<ScreenData>
        get() = _screenData

    init {
        _status.value = ApiStatus.LOADING
        saveCharactersPhotos()
        viewModelScope.launch {
            try {
                sendCharactersPhotos()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
            }
        }
    }

    private fun saveCharactersPhotos() {
        viewModelScope.launch {
            try {
                val characters: List<CharacterInfo> =
                    apiService.getCharacters().data.results.map { getCharacterInfo(it.name) }
                charactersRepository.refreshCharacters(
                    characters.map {
                        DatabaseCharacterInfo(
                            url = it.url,
                            description = it.description
                        )
                    }
                )
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
            }
        }
    }

    private suspend fun sendCharactersPhotos() {
        val characters = charactersRepository.getCharacters().map {
            CharacterInfo(
                url = it.url,
                description = it.description
            )
        }
        screenData.value = screenData.value.copy(characters = characters)
    }

    private suspend fun getCharacterInfo(name: String): CharacterInfo {
        try {
            val character = apiService.getCharacter(name)

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