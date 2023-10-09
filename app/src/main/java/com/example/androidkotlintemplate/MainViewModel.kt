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

interface MainViewModel {
    val screenData: MutableStateFlow<ScreenData>
    fun onClicked(id: Int)
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

    override fun onClicked(id: Int) {
        Log.i("Test", "Clicked:$id")
    }

    init {
        _status.value = ApiStatus.LOADING
        saveCharactersPhotos()
        updateUi()
    }

    private fun saveCharactersPhotos() {
        viewModelScope.launch {
            try {
                cacheImages()
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
            }
        }
    }

    private fun updateUi() {
        viewModelScope.launch {
            try {
                sendCharactersPhotos()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
            }
        }
    }

    private suspend fun cacheImages() {
        val characters: List<CharacterInfo> =
            apiService.getCharacters().data.results.map { getCharacterInfo(it.name) }
        charactersRepository.refreshCharacters(
            characters.map {
                DatabaseCharacterInfo(
                    id = it.id,
                    name = it.name,
                    url = it.url,
                    description = it.description,
                )
            }
        )
    }

    private suspend fun sendCharactersPhotos() {
        val characters = charactersRepository.getCharacters().map {
            CharacterInfo(
                id = it.id,
                name = it.name,
                url = it.url,
                description = it.description,
                onClick = { id -> onClicked(id = id) }
            )
        }
        screenData.value = screenData.value.copy(characters = characters)
    }

    private suspend fun getCharacterInfo(name: String): CharacterInfo {
        try {
            val character = apiService.getCharacter(name)

            return CharacterInfo(
                id = character.data.results[0].id,
                name = character.data.results[0].name,
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