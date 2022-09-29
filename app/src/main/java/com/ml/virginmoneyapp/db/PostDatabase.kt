package com.ml.virginmoneyapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ml.virginmoneyapp.db.DatabaseConstants.DATABASE_VERSION
import com.ml.virginmoneyapp.db.DatabaseConstants.EXPORT_SCHEMA
import com.ml.virginmoneyapp.domain.VirginKeys
import com.ml.virginmoneyapp.domain.Room

@Database(entities = [Room::class, VirginKeys::class], version = DATABASE_VERSION, exportSchema = EXPORT_SCHEMA)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postsDao() : PostsDao
    abstract fun keysDao(): KeysDao
}