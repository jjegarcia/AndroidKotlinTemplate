package com.example.androidkotlintemplate

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface MainViewModel {
    val screenData: MutableStateFlow<ScreenData>
}

@HiltViewModel
class MainViewModelImpl @Inject constructor(val context: Context) : ViewModel(), MainViewModel {
    private val _screenData = MutableStateFlow(
        ScreenData(
            "Test",
            R.drawable.ic_launcher_background,
        )
    )
    override val screenData: MutableStateFlow<ScreenData>
        get() = _screenData
}