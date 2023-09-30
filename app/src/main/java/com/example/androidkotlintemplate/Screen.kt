package com.example.androidkotlintemplate

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun Greeting(viewModel: MainViewModelImpl, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${viewModel.screenName}!",
        modifier = modifier
    )
    Image(painter = painterResource(id = viewModel.introImageId), contentDescription = "Intro Image")
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AndroidKotlinTemplateTheme {
//        Greeting(MainViewModelImpl(this@MainActivity))
//    }
//}