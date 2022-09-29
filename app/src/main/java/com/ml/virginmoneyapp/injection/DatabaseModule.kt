package com.ml.virginmoneyapp.network

import android.app.Application
import androidx.room.Room
import com.ml.virginmoneyapp.db.DatabaseConstants.DATABASE_NAME
import com.ml.virginmoneyapp.db.PostDatabase
import com.ml.virginmoneyapp.db.PostsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {

    fun provideDatabase(application: Application) : PostDatabase {
        return Room.databaseBuilder(application, PostDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideDatabase(androidApplication()) }
}