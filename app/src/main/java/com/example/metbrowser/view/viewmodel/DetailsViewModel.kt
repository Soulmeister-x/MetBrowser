package com.example.metbrowser.view.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.service.repository.SearchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "DetailsViewModel"

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val searchResultRepository: SearchResultRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _searchResult = MutableLiveData<SearchResult>()
    val searchResult: LiveData<SearchResult> = _searchResult

    init {
        savedStateHandle.get<Int>("objectId")?.let {
            loadSearchResult(it)
        }
    }

    private fun loadSearchResult(id: Int) {
        viewModelScope.launch {
            try {
                _searchResult.value = searchResultRepository.getObject(id)
            } catch (e: Exception) {
                // Retrofit error
                e.printStackTrace()
            }
        }
    }
}