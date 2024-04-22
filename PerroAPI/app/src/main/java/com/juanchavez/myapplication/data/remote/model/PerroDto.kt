package com.juanchavez.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class PerroDto(

    @SerializedName("id")
    var idPerro: Int? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("thumbnail")
    var thumbnail: String? = null

)