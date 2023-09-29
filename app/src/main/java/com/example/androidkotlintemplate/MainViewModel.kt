package com.example.androidkotlintemplate

import androidx.lifecycle.ViewModel

interface MainViewModel {
    val screenName: String
}

class MainViewModelImpl : ViewModel(),MainViewModel{
    override val screenName get() = "Test"
}