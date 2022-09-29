package com.ml.virginmoneyapp.injection

import com.ml.virginmoneyapp.repository.VirginRepository
import com.ml.virginmoneyapp.ui.viewmodel.VirginViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { VirginViewModel(get()) }
}