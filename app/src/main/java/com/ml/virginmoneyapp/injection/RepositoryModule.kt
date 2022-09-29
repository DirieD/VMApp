package com.ml.virginmoneyapp.injection

import com.ml.virginmoneyapp.repository.VirginRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { VirginRepository(get(), get()) }
}