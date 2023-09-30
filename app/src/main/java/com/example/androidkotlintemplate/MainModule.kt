package com.example.androidkotlintemplate

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
* Hilt module that provides singleton (application-scoped) objects.
*/
@Module
@InstallIn(SingletonComponent::class)
class MainModule {
//    @Singleton
//    @Provides
//    fun provideHealthServicesClient(@ApplicationContext context: Context): HealthServicesClient =
//        HealthServices.getClient(context)

//    @Singleton
//    @Provides
//    fun provideDatabaseHandler(): DatabaseHandlerI = DatabaseHandler()

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context
}
