package com.ml.virginmoneyapp.domain

import com.google.gson.annotations.SerializedName

data class Data (

    @SerializedName("modhash") val modhash : String,
    @SerializedName("dist") val dist : Int,
    @SerializedName("children") val children : List<Children>,
    @SerializedName("after") val after : String?,
    @SerializedName("before") val before : String?
)