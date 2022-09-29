package com.ml.virginmoneyapp.network

import androidx.paging.PagingSource
import com.ml.virginmoneyapp.domain.Room
import com.ml.virginmoneyapp.domain.toRoom
import retrofit2.HttpException
import java.io.IOException

class VirginMoneyDataSource(private val virginApi: VirginApi) :
    PagingSource<String, Room>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, Room> {
         return try {
            val response = virginApi.getPosts(params.loadSize)
            val listing = response.data
            val posts = listing.children.map { it.data.toRoom() }

            LoadResult.Page(
                posts,
                listing.before,
                listing.after
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override val keyReuseSupported: Boolean = true
}