package com.example.androidkotlintemplate

import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

private const val PRIVATE_KEY = "a42291623deb20ef0c3c91a3b17cb5017a8a30f1"
private const val PUBLIC_KEY = "e2d61498137ae803a0e479fecea99882"
private const val TS = "1"


private fun hash(): String = BigInteger(
    1,
    MessageDigest.getInstance("MD5").digest("$TS$PRIVATE_KEY$PUBLIC_KEY".toByteArray())
)
    .toString(16).padStart(32, '0')

interface ApiService {
    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("ts") ts: String = TS,
        @Query("apikey") apikey: String = PUBLIC_KEY,
        @Query("hash") hash: String = hash()
    ): ComicsResponse

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts") ts: String = TS,
        @Query("apikey") apikey: String = PUBLIC_KEY,
        @Query("hash") hash: String = hash()
    ): CharactersResponse

    @GET("/v1/public/characters")
    suspend fun getCharacter(
        @Query("name") name: String,
        @Query("ts") ts: String = TS,
        @Query("apikey") apikey: String = PUBLIC_KEY,
        @Query("hash") hash: String = hash()
    ): CharactersResponse
}