package com.ml.virginmoneyapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ml.virginmoneyapp.domain.Room
import com.ml.virginmoneyapp.repository.VirginRepository
import kotlinx.coroutines.flow.Flow

class VirginViewModel(
    private val virginRepository: VirginRepository
) : ViewModel() {

    fun fetchPosts() : Flow<PagingData<Room>> {
        return virginRepository.fetchPosts().cachedIn(viewModelScope)
    }
}