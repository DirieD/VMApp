package com.ml.virginmoneyapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ml.virginmoneyapp.domain.VirginKeys

@Dao
interface KeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveVirginKeys(virginKey: VirginKeys)

    @Query("SELECT * FROM VirginKeys ORDER BY id DESC")
    suspend fun getVirginKeys(): List<VirginKeys>

}