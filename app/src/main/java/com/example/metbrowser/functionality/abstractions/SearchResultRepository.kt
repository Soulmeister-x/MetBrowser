package com.example.metbrowser.functionality.abstractions

import com.example.metbrowser.functionality.data.api.model.ApiSearchResult
import com.example.metbrowser.functionality.data.api.model.ApiSearchResultList
import com.example.metbrowser.functionality.entities.ObjectDetails
import com.example.metbrowser.functionality.entities.SearchResult
import kotlinx.coroutines.flow.Flow

/**
 * The goal of this class is to abstract the data source from the rest of the app.
 */
interface SearchResultRepository{

    fun getObject(id: Int): Flow<ObjectDetails>

    suspend fun getResultsIds(search: String): SearchResult
}