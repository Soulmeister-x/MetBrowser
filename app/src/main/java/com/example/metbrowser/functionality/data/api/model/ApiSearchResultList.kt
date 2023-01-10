package com.example.metbrowser.functionality.data.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiSearchResultList (
    @SerializedName("total")
    @Expose
    var total: Int,

    @SerializedName("objectIDs")
    @Expose
    var objectIds: List<Int>
)