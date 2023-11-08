package com.example.androidkotlintemplate.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable

fun StateSample() {
    var text = ""
    Column {
        TextField(value = text, onValueChange = { text = it })
        Text(text)
        Button(onClick = { text = "" }) {
            Text(text = "Clear")
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
//@Composable
//fun StateSample2() {
//    val text = remember { mutableStateOf("") }
//    Column {
//        TextField(value = text.value, onValueChange = { text.value = it })
//        Text(text.value)
//        Button(onClick = { text.value = "" }) {
//            Text(text = "Clear")
//        }
//    }
//}
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun StateSample3() {
    var text by remember { mutableStateOf("") }
    Column {
        TextField(value = text, onValueChange = { text = it })
        Text(text)
        Button(onClick = { text = "" }) {
            Text(text = "Clear")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun Parent() { //apply state hoisting
    var text by rememberSaveable { mutableStateOf("") }
    StateSample4(text) { text = it }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateSample4(value: String = "", onChange: (String) -> Unit) {
    Column {
        TextField(value = value, onValueChange = { onChange(it) })
        Text(value)
        Button(onClick = { onChange("") }) {
            Text(text = "Clear")
        }
    }
}