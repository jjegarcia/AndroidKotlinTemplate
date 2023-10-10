package com.example.androidkotlintemplate

import android.content.Context
import androidx.room.Room
import com.example.androidkotlintemplate.database.CharactersDatabase
import com.example.androidkotlintemplate.network.ApiService
import com.example.androidkotlintemplate.network.CharactersRepository
import com.example.androidkotlintemplate.network.CharactersRepositoryImpl
import com.example.androidkotlintemplate.weblink.WebLinkLauncher
import com.example.androidkotlintemplate.weblink.WeblinkLauncherImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://gateway.marvel.com"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

@Module
@InstallIn(SingletonComponent::class)
class MainModule {
    private lateinit var INSTANCE: CharactersDatabase

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): CharactersDatabase {
        synchronized(CharactersDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDatabase::class.java,
                    "characters"
                ).build()
            }
        }
        return INSTANCE
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideWeblinkLauncher(@ApplicationContext context: Context): WebLinkLauncher =
        WeblinkLauncherImpl(context)

    @Singleton
    @Provides
    fun provideCharactersRepository(database: CharactersDatabase): CharactersRepository =
        CharactersRepositoryImpl(database)
}
