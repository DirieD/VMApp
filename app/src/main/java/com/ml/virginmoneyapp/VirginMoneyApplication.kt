package com.ml.virginmoneyapp

import android.app.Application
import com.ml.virginmoneyapp.injection.repositoryModule
import com.ml.virginmoneyapp.injection.viewModelModule
import com.ml.virginmoneyapp.network.databaseModule
import com.ml.virginmoneyapp.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class VirginMoneyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@VirginMoneyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }

}