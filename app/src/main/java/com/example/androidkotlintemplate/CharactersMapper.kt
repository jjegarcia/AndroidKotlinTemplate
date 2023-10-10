package com.example.androidkotlintemplate

import android.util.Log
import com.example.androidkotlintemplate.database.DatabaseCharacterInfo
import com.example.androidkotlintemplate.weblink.WebLinkLauncher
import javax.inject.Inject

interface CharactersMapper {
    fun onClicked(url: String)
fun mapCharacters(characters: List<DatabaseCharacterInfo>): List<CharacterInfo>
}

class CharactersMapperImpl @Inject constructor(
    private val webLinkLauncher: WebLinkLauncher
    ) : CharactersMapper {
    override fun mapCharacters(characters: List<DatabaseCharacterInfo>): List<CharacterInfo> =
        characters.map {
            CharacterInfo(
                id = it.id,
                name = it.name,
                url = it.url,
                description = it.description,
                onClick = { url -> onClicked(url = url) }
            )

        }
    override fun onClicked(url: String) {
        Log.i("Test", "Clicked:$url")
        webLinkLauncher.launchApplication(uRL = url)
    }

}
