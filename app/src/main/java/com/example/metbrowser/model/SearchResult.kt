package com.example.metbrowser.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class for a single [SearchResult], to display in DetailScreen
 *
 * @SerializedName indicates that the property should be serialized with the specified name.
 * @Expose indicates that the property should be exposed for JSON serialization and deserialization.
 */
data class SearchResult(
    @SerializedName("objectID")
    @Expose
    var objectId: Int,

    @SerializedName("primaryImage")
    @Expose
    var primaryImage: String, // url to image

    @SerializedName("additionalImages")
    @Expose
    var additionalImages: List<String>,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("artistDisplayName")
    @Expose
    var artist: String,

    @SerializedName("objectURL")
    @Expose
    var objectUrl: String

)