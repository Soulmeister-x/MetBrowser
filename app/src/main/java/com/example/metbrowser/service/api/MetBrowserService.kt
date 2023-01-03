package com.example.metbrowser.service.api

import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList
import retrofit2.http.GET
import retrofit2.http.Path

interface MetBrowserService {
    @GET("public/collection/v1/objects/{id}")
    suspend fun getTestObject(@Path(value = "id") id: Int): SearchResult

    @GET("public/collection/v1/search?q=flower")
    suspend fun getFlowerResults(): SearchResultList
}