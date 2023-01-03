package com.example.metbrowser.service.repository

import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList

/**
 * The goal of this class is to abstract the data source from the rest of the app.
 */
interface SearchResultRepository{
    suspend fun getTestObject(id: Int): SearchResult

    suspend fun getResultsIds(): SearchResultList
}