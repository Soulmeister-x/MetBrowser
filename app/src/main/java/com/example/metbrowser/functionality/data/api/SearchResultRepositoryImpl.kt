package com.example.metbrowser.functionality.data.api

import com.example.metbrowser.functionality.abstractions.SearchResultRepository
import com.example.metbrowser.functionality.data.api.mapper.mapToEntity
import com.example.metbrowser.functionality.entities.ObjectDetails
import com.example.metbrowser.functionality.entities.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * The goal of this class is to abstract the data source from the rest of the app.
 */
class SearchResultRepositoryImpl(
    private val metBrowserService: MetBrowserService
) : SearchResultRepository {
    override fun getObject(id: Int): Flow<ObjectDetails> {
        return flow {
            emit(metBrowserService.getObject(id).mapToEntity())
        }
    }

    override suspend fun getResultsIds(search: String): SearchResult =
        metBrowserService.getObjectIds(search).mapToEntity()
}