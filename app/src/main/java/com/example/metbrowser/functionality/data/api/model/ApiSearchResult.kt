package com.example.metbrowser.functionality.data.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Data class for a single [ApiSearchResult], to display in DetailScreen
 *
 * @SerializedName indicates that the property should be serialized with the specified name.
 * @Expose indicates that the property should be exposed for JSON serialization and deserialization.
 */
data class ApiSearchResult(
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

) {
    override fun toString(): String {
        return "SearchResult" +
                "\nobjectId=$objectId," +
                "\nprimaryImage=$primaryImage," +
                "\nadditionalImages=$additionalImages," +
                "\ntitle=$title," +
                "\nartist=$artist," +
                "\nobjectUrl=$objectUrl"
    }
}