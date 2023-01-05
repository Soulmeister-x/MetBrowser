package com.example.metbrowser.service.api

import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetBrowserService {
    @GET("public/collection/v1/objects")
    suspend fun getObject(
        @Query("id") id: Int
    ): SearchResult

    @GET("public/collection/v1/search?q=flower")
    suspend fun getFlowerResults(): SearchResultList

    @GET("public/collection/v1/search")
    suspend fun getObjectIds(
        @Query("q") search: String
    ): SearchResultList
}