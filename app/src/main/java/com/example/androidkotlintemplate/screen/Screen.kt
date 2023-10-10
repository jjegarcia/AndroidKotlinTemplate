package com.example.androidkotlintemplate.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.R.drawable
import coil.compose.AsyncImage
import com.example.androidkotlintemplate.R

data class CharacterInfo(
    val id: Int = -1,
    val name: String = "",
    val url: String = "",
    val description: String = "",
    val onClick: (String) -> Unit = {}
)

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
            itemsIndexed(screenData.characters) { index, _ ->
                CharacterCard(screenData.characters[index])
                Divider(thickness = 10.dp, color = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterCard(characterInfo: CharacterInfo) {
    Card(onClick = { characterInfo.onClick(characterInfo.url) }) {
        Text(text = characterInfo.name)
        Text(text = characterInfo.description)
        AsyncImage(
            model = characterInfo.url,
            error = painterResource(id = drawable.ic_call_decline),
            fallback = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Intro Image"
        )
    }
}