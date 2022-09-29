package com.ml.virginmoneyapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Room(
    @PrimaryKey
    val title : String,
    val author : String,
    val url: String
)