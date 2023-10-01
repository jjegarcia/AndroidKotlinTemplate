package com.example.androidkotlintemplate

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.net.URI
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

interface MainViewModel {
    val screenData: MutableStateFlow<ScreenData>
}

@HiltViewModel
class MainViewModelImpl @Inject constructor(val context: Context) : ViewModel(), MainViewModel {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ApiStatus> = _status

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsPhoto
    // with new values
    private val _photos = MutableLiveData<List<MarsPhoto>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val photos: LiveData<List<MarsPhoto>> = _photos


    private val _screenData = MutableStateFlow(
        ScreenData(
            "Test",
            getImage(null),
            "https://res.cloudinary.com/demo/image/upload/v1212461204/sample.jpg"
        )
    )

    private fun getImage(uri: URI?) = if (uri == null) R.drawable.ic_launcher_background
    else
        getImageFromOnline(uri)

    private fun getImageFromOnline(uri: URI): Int = androidx.core.R.drawable.ic_call_answer
    override val screenData: MutableStateFlow<ScreenData>
        get() = _screenData

    init {
        getMarsPhotos()
        if(_photos.value?.isEmpty() == false){
            _screenData.value.copy(url = "https://res.cloudinary.com/demo/image/upload/v1212461204/sample.jpg"
            )
        }
    }

    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}