package com.ml.virginmoneyapp.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ml.virginmoneyapp.db.PostDatabase
import com.ml.virginmoneyapp.domain.Room
import com.ml.virginmoneyapp.domain.toRoom
import com.ml.virginmoneyapp.domain.VirginKeys
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class VirginRemoteMediator(
    private val virginApi: VirginApi,
    private val VirginDatabase: PostDatabase
) : RemoteMediator<Int, Room>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Room>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getVirginKeys()
                }
            }

            val response = virginApi.getPosts(
                loadSize = state.config.pageSize,
                after = loadKey?.after,
                before = loadKey?.before
            )
            val listing = response.data
            val rooms = listing.children.map { it.data.toRoom() }

            VirginDatabase.withTransaction {
                VirginDatabase.keysDao()
                    .saveVirginKeys(VirginKeys(0, listing.after, listing.before))
                VirginDatabase.postsDao().savePosts(rooms)
            }
            MediatorResult.Success(endOfPaginationReached = listing.after == null)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getVirginKeys(): VirginKeys? {
        return VirginDatabase.keysDao().getVirginKeys().firstOrNull()
    }
}