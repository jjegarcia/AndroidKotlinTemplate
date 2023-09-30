package com.example.androidkotlintemplate

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

data class ScreenData(
    val screenName: String,
    val introImageId: Int
)
@Composable
fun Greeting(viewModel: MainViewModelImpl, modifier: Modifier = Modifier) {

    val screenData = viewModel.screenData.collectAsState().value

    Text(
        text = "Hello ${screenData.screenName}!",
        modifier = modifier
    )
    Image(painter = painterResource(id = screenData.introImageId), contentDescription = "Intro Image")
}