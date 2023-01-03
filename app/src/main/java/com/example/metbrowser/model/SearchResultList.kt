package com.example.metbrowser.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResultList (
    @SerializedName("total")
    @Expose
    var total: Int,

    @SerializedName("objectIDs")
    @Expose
    var objectIds: List<Int>
)