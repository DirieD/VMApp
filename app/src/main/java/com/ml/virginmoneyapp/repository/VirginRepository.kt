package com.ml.virginmoneyapp.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ml.virginmoneyapp.db.PostDatabase
import com.ml.virginmoneyapp.domain.Room
import com.ml.virginmoneyapp.network.VirginApi
import com.ml.virginmoneyapp.network.VirginMoneyDataSource
import com.ml.virginmoneyapp.network.VirginRemoteMediator
import kotlinx.coroutines.flow.Flow

class VirginRepository(
    private val virginApi: VirginApi,
    private val virginDatabase: PostDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<Room>> {
        return Pager(
            PagingConfig(
                pageSize = 40,
                enablePlaceholders = false,
                prefetchDistance = 3),

            remoteMediator = VirginRemoteMediator(virginApi, virginDatabase),
            pagingSourceFactory = { virginDatabase.postsDao().getPosts() }

        ).flow
    }
}