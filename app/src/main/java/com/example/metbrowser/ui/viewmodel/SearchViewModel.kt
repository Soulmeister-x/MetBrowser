package com.example.metbrowser.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metbrowser.functionality.abstractions.SearchResultRepository
import com.example.metbrowser.functionality.entities.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val TAG = "SearchViewModel"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchResultRepository: SearchResultRepository
) : ViewModel() {

    private val searchText = MutableSharedFlow<String>(replay = 1)

    val result: StateFlow<SearchResult> = searchText
        .map { searchResultRepository.getResultsIds(search = it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, SearchResult(emptyList()))

    fun onSearchTextChanged(newSearchText: String) {
        searchText.tryEmit(newSearchText)
    }
}