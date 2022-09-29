package com.ml.virginmoneyapp.domain

import com.google.gson.annotations.SerializedName

data class Response (
	@SerializedName("kind") val kind : String,
	@SerializedName("data") val data : Data
)