package com.example.androidkotlintemplate.screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidkotlintemplate.database.CharactersDatabaseMapper
import com.example.androidkotlintemplate.network.ApiMapper
import com.example.androidkotlintemplate.network.ApiService
import com.example.androidkotlintemplate.network.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

interface MainViewModel {
    val screenData: MutableStateFlow<ScreenData>
}

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val apiService: ApiService,
    private val charactersRepository: CharactersRepository,
    private val charactersUiMapper: CharactersUiMapper,
    private val charactersDatabaseMapper: CharactersDatabaseMapper,
    private val apiMapper: ApiMapper
) : ViewModel(), MainViewModel, ApiService by apiService,
    CharactersRepository by charactersRepository {

    private val _status = MutableLiveData<ApiStatus>()
    private val _screenData = MutableStateFlow(ScreenData())


    override val screenData: MutableStateFlow<ScreenData>
        get() = _screenData

    init {
        _status.value = ApiStatus.LOADING
        saveCharactersPhotos()
        updateUi()
    }

    private fun saveCharactersPhotos() {
        viewModelScope.launch {
            try {
                charactersRepository.refreshCharacters(
                    charactersDatabaseMapper.mapCharacters(getCharacters().data.results
                        .map { getCharacterInfo(it.name) }
                    )
                )
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
            }
        }
    }

    private fun updateUi() {
        viewModelScope.launch {
            try {
                screenData.value =
                    screenData.value.copy(characters = charactersUiMapper.mapCharacters(characters()))
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.i("Error:", e.toString())
            }
        }
    }

    private suspend fun getCharacterInfo(name: String): CharacterInfo {
        try {
            return apiMapper.getCharacter(name)
        } catch (e: Exception) {
            Log.i("Error:", e.toString())
            _status.value = ApiStatus.ERROR
        }
        return CharacterInfo()
    }
}