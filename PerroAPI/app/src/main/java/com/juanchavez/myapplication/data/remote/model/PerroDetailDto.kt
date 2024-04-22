package com.juanchavez.myapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class PerroDetailDto(

    @SerializedName("name")
    var name : String?,

    @SerializedName("image")
    var image : String?,

    @SerializedName("desc")
    var description : String?,

    @SerializedName("life_Years")
    var years : Int?,

    @SerializedName("height")
    var height : Int?,

    @SerializedName("size")
    var size : String?

)

