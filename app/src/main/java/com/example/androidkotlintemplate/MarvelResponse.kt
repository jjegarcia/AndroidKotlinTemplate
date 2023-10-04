package com.example.androidkotlintemplate

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvellResponse(
    @field:Json(name = "attributionHTML") val attributionHTML: String,
    @field:Json(name = "attributionText") val attributionText: String,
    @field:Json(name = "code") val code: Int,
    @field:Json(name = "copyright") val copyright: String,
    @field:Json(name = "data") val data: Data,
    @field:Json(name = "etag") val etag: String,
    @field:Json(name = "status") val status: String
)

@JsonClass(generateAdapter = true)
data class Data(
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "limit") val limit: Int,
    @field:Json(name = "offset") val offset: Int,
    @field:Json(name = "results") val results: List<Result>,
    @field:Json(name = "total") val total: Int
)

@JsonClass(generateAdapter = true)
data class Result(
    @field:Json(name = "characters") val characters: Characters,
    @field:Json(name = "collectedIssues") val collectedIssues: List<CollectedIssue>,
    @field:Json(name = "collections") val collections: List<Any>,
    @field:Json(name = "creators") val creators: Creators,
    @field:Json(name = "dates") val dates: List<Date>,
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "diamondCode") val diamondCode: String,
    @field:Json(name = "digitalId") val digitalId: Int,
    @field:Json(name = "ean") val ean: String,
    @field:Json(name = "events") val events: Events,
    @field:Json(name = "format") val format: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "images") val images: List<Image>,
    @field:Json(name = "isbn") val isbn: String,
    @field:Json(name = "issn") val issn: String,
    @field:Json(name = "issueNumber") val issueNumber: Int,
    @field:Json(name = "modified") val modified: String,
    @field:Json(name = "pageCount") val pageCount: Int,
    @field:Json(name = "prices") val prices: List<Price>,
    @field:Json(name = "resourceURI") val resourceURI: String,
    @field:Json(name = "series") val series: Series,
    @field:Json(name = "stories") val stories: Stories,
    @field:Json(name = "textObjects") val textObjects: List<TextObject>,
    @field:Json(name = "thumbnail") val thumbnail: Thumbnail,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "upc") val upc: String,
    @field:Json(name = "urls") val urls: List<Url>,
    @field:Json(name = "variantDescription") val variantDescription: String,
    @field:Json(name = "variants") val variants: List<Variant>
)

@JsonClass(generateAdapter = true)
data class Characters(
    @field:Json(name = "available") val available: Int,
    @field:Json(name = "collectionURI") val collectionURI: String,
    @field:Json(name = "items") val items: List<Item>,
    @field:Json(name = "returned") val returned: Int
)

@JsonClass(generateAdapter = true)
data class CollectedIssue(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String
)

@JsonClass(generateAdapter = true)
data class Creators(
    @field:Json(name = "available") val available: Int,
    @field:Json(name = "collectionURI") val collectionURI: String,
    @field:Json(name = "items") val items: List<ItemX>,
    @field:Json(name = "returned") val returned: Int
)

@JsonClass(generateAdapter = true)
data class Date(
    @field:Json(name = "date") val date: String,
    @field:Json(name = "type") val type: String
)

@JsonClass(generateAdapter = true)
data class Events(
    @field:Json(name = "available") val available: Int,
    @field:Json(name = "collectionURI") val collectionURI: String,
    @field:Json(name = "items") val items: List<Any>,
    @field:Json(name = "returned") val returned: Int
)

@JsonClass(generateAdapter = true)
data class Image(
    @field:Json(name = "extension") val extension: String,
    @field:Json(name = "path") val path: String
)

@JsonClass(generateAdapter = true)
data class Price(
    @field:Json(name = "price") val price: Double,
    @field:Json(name = "type") val type: String
)

@JsonClass(generateAdapter = true)
data class Series(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String
)

@JsonClass(generateAdapter = true)
data class Stories(
    @field:Json(name = "available") val available: Int,
    @field:Json(name = "collectionURI") val collectionURI: String,
    @field:Json(name = "items") val items: List<ItemXX>,
    @field:Json(name = "returned") val returned: Int
)

@JsonClass(generateAdapter = true)
data class TextObject(
    @field:Json(name = "language") val language: String,
    @field:Json(name = "text") val text: String,
    @field:Json(name = "type") val type: String
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
data class Variant(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String
)

@JsonClass(generateAdapter = true)
data class Item(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String
)

@JsonClass(generateAdapter = true)
data class ItemX(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String,
    @field:Json(name = "role") val role: String
)

@JsonClass(generateAdapter = true)
data class ItemXX(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "resourceURI") val resourceURI: String,
    @field:Json(name = "type") val type: String
)