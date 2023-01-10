package com.example.metbrowser.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.metbrowser.functionality.abstractions.SearchResultRepository
import com.example.metbrowser.functionality.entities.ObjectDetails
import com.example.metbrowser.ui.ui.DetailFragmentArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(
    searchResultRepository: SearchResultRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val objectDetails: Flow<ObjectDetails> =
        searchResultRepository.getObject(
            DetailFragmentArgs.fromSavedStateHandle(savedStateHandle).objectId
        )
}