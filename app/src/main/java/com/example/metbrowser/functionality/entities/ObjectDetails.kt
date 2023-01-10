package com.example.metbrowser.functionality.entities

data class ObjectDetails(
    var objectId: Int,
    var primaryImage: String,
    var additionalImages: List<String>,
    var title: String,
    var artist: String,
    var objectUrl: String
)
