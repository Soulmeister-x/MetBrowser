package com.example.metbrowser.functionality.data.api

import com.example.metbrowser.functionality.data.api.model.ApiSearchResult
import com.example.metbrowser.functionality.data.api.model.ApiSearchResultList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetBrowserService {
    @GET("public/collection/v1/objects/{id}")
    suspend fun getObject(
        @Path("id") id: Int
    ): ApiSearchResult

    @GET("public/collection/v1/search")
    suspend fun getObjectIds(
        @Query("q") search: String
    ): ApiSearchResultList
}