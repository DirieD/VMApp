package com.ml.virginmoneyapp.network

import com.ml.virginmoneyapp.domain.Response
import com.ml.virginmoneyapp.network.NetworkConstants.GET_POSTS_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Query

interface VirginApi {

    @GET(GET_POSTS_ENDPOINT)
    suspend fun getPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response
}