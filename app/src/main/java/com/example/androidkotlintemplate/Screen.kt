package com.example.androidkotlintemplate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.core.R.drawable
import coil.compose.AsyncImage

data class ScreenData(
    val screenName: String = "Test",
    val characters: List<CharacterInfo> = listOf()
)

@Composable
fun Greeting(viewModel: MainViewModelImpl, modifier: Modifier = Modifier) {

    val screenData = viewModel.screenData.collectAsState().value

    Column {
        Text(
            text = "Hello ${screenData.screenName}!",
            modifier = modifier
        )
        LazyColumn {
            itemsIndexed(screenData.characters) { index, item ->
                Text(text = screenData.characters.get(index).description)
                AsyncImage(
                    model = screenData.characters.get(index).url,
                    error = painterResource(id = drawable.ic_call_decline),
                    fallback = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Intro Image"
                )
            }
        }
    }
}