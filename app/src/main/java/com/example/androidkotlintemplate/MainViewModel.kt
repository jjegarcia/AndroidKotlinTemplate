package com.example.androidkotlintemplate

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface MainViewModel {
    val introImageId: Int
    val screenName: String
}

@HiltViewModel
class MainViewModelImpl @Inject constructor( val context: Context) : ViewModel(), MainViewModel {
    override val introImageId: Int
        get() = R.drawable.ic_launcher_background
    override val screenName get() = "Test"
}