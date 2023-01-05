package com.example.metbrowser.service.repository

import android.util.Log
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList
import com.example.metbrowser.service.api.MetBrowserService

/**
 * The goal of this class is to abstract the data source from the rest of the app.
 */
class SearchResultRepositoryImpl(
    private val metBrowserService: MetBrowserService
): SearchResultRepository {
    override suspend fun getObject(id: Int): SearchResult =
        metBrowserService.getObject(id)

    override suspend fun getResultsIds(search: String): SearchResultList {
        return metBrowserService.getObjectIds(search)
    }
}