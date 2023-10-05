package com.example.androidkotlintemplate

import com.squareup.moshi.Json

data class CharactersResponse(
    @field:Json(name= " attributionHTML")val attributionHTML: String,
    @field:Json(name= " attributionText")val attributionText: String,
    @field:Json(name= " code")val code: Int,
    @field:Json(name= " copyright")val copyright: String,
    @field:Json(name= " data")val data: CharactersData,
    @field:Json(name= " etag")val etag: String,
    @field:Json(name= " status")val status: String
)

data class CharactersData(
    @field:Json(name= " count")val count: Int,
    @field:Json(name= " limit")val limit: Int,
    @field:Json(name= " offset")val offset: Int,
    @field:Json(name= " results")val results: List<CharactersResult>,
    @field:Json(name= " total")val total: Int
)

data class CharactersResult(
    @field:Json(name= " comics")val comics: Comics,
    @field:Json(name= " description")val description: String,
    @field:Json(name= " events")val events: EventsCharacters,
    @field:Json(name= " id")val id: Int,
    @field:Json(name= " modified")val modified: String,
    @field:Json(name= " name")val name: String,
    @field:Json(name= " resourceURI")val resourceURI: String,
    @field:Json(name= " series")val series: CharactersSeries,
    @field:Json(name= " stories")val stories: Stories,
    @field:Json(name= " thumbnail")val thumbnail: Thumbnail,
    @field:Json(name= " urls")val urls: List<Url>
)

data class Comics(
    @field:Json(name= " available")val available: Int,
    @field:Json(name= " collectionURI")val collectionURI: String,
    @field:Json(name= " items")val items: List<Item>,
    @field:Json(name= " returned")val returned: Int
)

data class EventsCharacters(
    @field:Json(name= " available")val available: Int,
    @field:Json(name= " collectionURI")val collectionURI: String,
    @field:Json(name= " items")val items: List<Item>,
    @field:Json(name= " returned")val returned: Int
)

data class CharactersSeries(
    @field:Json(name= " available")val available: Int,
    @field:Json(name= " collectionURI")val collectionURI: String,
    @field:Json(name= " items")val items: List<CharactersItem>,
    @field:Json(name= " returned")val returned: Int
)

data class CharactersItem(
    @field:Json(name= " name")val name: String,
    @field:Json(name= " resourceURI")val resourceURI: String
)