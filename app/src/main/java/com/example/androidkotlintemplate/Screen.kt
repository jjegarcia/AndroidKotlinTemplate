package com.example.androidkotlintemplate

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidkotlintemplate.ui.theme.AndroidKotlinTemplateTheme

@Composable
fun Greeting(viewModel: MainViewModelImpl, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ${viewModel.screenName}!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidKotlinTemplateTheme {
        Greeting(MainViewModelImpl())
    }
}