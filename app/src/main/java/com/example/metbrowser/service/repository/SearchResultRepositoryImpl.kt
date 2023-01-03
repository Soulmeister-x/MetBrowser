package com.example.metbrowser.service.repository

import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList
import com.example.metbrowser.service.api.MetBrowserService

/**
 * The goal of this class is to abstract the data source from the rest of the app.
 */
class SearchResultRepositoryImpl(
    private val metBrowserService: MetBrowserService
): SearchResultRepository {
    override suspend fun getTestObject(id: Int): SearchResult =
        metBrowserService.getTestObject(id)

    override suspend fun getResultsIds(): SearchResultList =
        metBrowserService.getFlowerResults()
}