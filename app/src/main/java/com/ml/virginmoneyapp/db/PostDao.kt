package com.ml.virginmoneyapp.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ml.virginmoneyapp.domain.Room

@Dao
interface PostsDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePosts(rooms: List<Room>)

    @Query("SELECT * FROM Room")
    fun getPosts(): PagingSource<Int, Room>
}