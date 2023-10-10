package com.example.androidkotlintemplate.network

import com.example.androidkotlintemplate.screen.CharacterInfo
import javax.inject.Inject

interface ApiMapper {
    suspend fun getCharacter(name: String): CharacterInfo
}

class ApiMapperImpl @Inject constructor(
    private val apiService: ApiService,


    ) : ApiMapper,ApiService by apiService{
    override suspend fun getCharacter(name: String): CharacterInfo {
        val character = apiService.getCharacter(name)
        return CharacterInfo(
            id = character.data.results[0].id,
            name = character.data.results[0].name,
            url = getUrl(character.data.results[0].thumbnail),
            description = character.data.results[0].description
        )

    }
    private fun getUrl(thumbnail: Thumbnail): String =
        "${thumbnail.path}/portrait_incredible.${thumbnail.extension}"

}
