package com.ml.virginmoneyapp.domain

import com.google.gson.annotations.SerializedName

data class PostData(
    @SerializedName("author") val author : String,
    @SerializedName("title") val title : String,
    @SerializedName("url") val url: String
)

fun PostData.toRoom() =
    Room(this.title, this.author, this.url)