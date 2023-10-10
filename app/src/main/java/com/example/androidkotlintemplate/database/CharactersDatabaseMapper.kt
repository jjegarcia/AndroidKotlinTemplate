package com.example.androidkotlintemplate.database

import com.example.androidkotlintemplate.screen.CharacterInfo

interface CharactersDatabaseMapper {
    fun mapCharacters(characters: List<CharacterInfo>): List<DatabaseCharacterInfo>
}

class CharactersDatabaseMapperImpl : CharactersDatabaseMapper {
    override fun mapCharacters(characters: List<CharacterInfo>): List<DatabaseCharacterInfo> =
        characters.map {
            DatabaseCharacterInfo(
                id = it.id,
                name = it.name,
                url = it.url,
                description = it.description,
            )
        }
}

