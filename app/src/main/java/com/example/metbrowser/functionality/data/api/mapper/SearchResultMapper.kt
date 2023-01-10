package com.example.metbrowser.functionality.data.api.mapper

import com.example.metbrowser.functionality.data.api.model.ApiSearchResult
import com.example.metbrowser.functionality.data.api.model.ApiSearchResultList
import com.example.metbrowser.functionality.entities.ObjectDetails
import com.example.metbrowser.functionality.entities.SearchResult

fun ApiSearchResultList.mapToEntity() = SearchResult(
    objectIds = objectIds
)

fun ApiSearchResult.mapToEntity() = ObjectDetails(
    objectId = objectId,
    primaryImage = primaryImage,
    additionalImages = additionalImages,
    title = title,
    artist = artist,
    objectUrl = objectUrl
)