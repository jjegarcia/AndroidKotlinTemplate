package com.example.androidkotlintemplate

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest

private const val BASE_URL =
    "http://gateway.marvel.com"///v1/public/comics?ts=1&apikey=e2d61498137ae803a0e479fecea99882&hash=862b80be04dd5c76badbb19000f98a02"
private const val PRIVATE_KEY = "a42291623deb20ef0c3c91a3b17cb5017a8a30f1"
private const val PUBLIC_KEY = "e2d61498137ae803a0e479fecea99882"
private const val TS = "1"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun hash(): String =
    BigInteger(
        1,
        MessageDigest.getInstance("MD5").digest("1$PRIVATE_KEY$PUBLIC_KEY".toByteArray())
    )
        .toString(16).padStart(32, '0')

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getCharacters] method
 */
interface ApiService {
     @GET("/v1/public/comics")
    suspend fun getCharacters(
        @Query("ts") ts: String = TS,
        @Query("apikey") apikey: String = PUBLIC_KEY,
        @Query("hash")hash:String= hash()
    ): MarvellResponse
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
//data class MarsPhoto(
//    val id: String,
//    // used to map img_src from the JSON to imgSrcUrl in our class
//    @Json(name = "img_src") val imgSrcUrl: String
//)