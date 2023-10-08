package com.example.androidkotlintemplate.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stories(
    @field:Json(name = "available") val available: Int,
    @field:Json(name = "collectionURI") val collectionURI: String,
    @field:Json(name = "items") val items: List<ItemXX>,
    @field:Json(name = "returned") val returned: Int
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @field:Json(name = "extension") val extension: String,
    @field:Json(name = "path") val path: String
)

@JsonClass(generateAdapter = true)
data class Url(
    @field:Json(name = "type") val type: String,
    @field:Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class Item(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String
)

@JsonClass(generateAdapter = true)
data class ItemXX(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String,
    @field:Json(name = "type") val type: String
)