package com.example.androidkotlintemplate

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface MainViewModel {
    val screenName: String
}

@HiltViewModel
class MainViewModelImpl @Inject constructor(): ViewModel(),MainViewModel{
    override val screenName get() = "Test"
}