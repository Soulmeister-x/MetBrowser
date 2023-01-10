package com.example.metbrowser.di

import com.example.metbrowser.functionality.data.api.MetBrowserService
import com.example.metbrowser.functionality.abstractions.SearchResultRepository
import com.example.metbrowser.functionality.data.api.SearchResultRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * provides my repository instance in all of the application
     */
    @Singleton
    @Provides
    fun provideSearchResultRepository(
        metBrowserService: MetBrowserService
    ): SearchResultRepository =
        SearchResultRepositoryImpl(metBrowserService)
}