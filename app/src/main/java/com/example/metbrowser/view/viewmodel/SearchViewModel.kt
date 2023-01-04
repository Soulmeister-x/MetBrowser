package com.example.metbrowser.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList
import com.example.metbrowser.service.repository.SearchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchResultRepository: SearchResultRepository
): ViewModel() {
    // TODO: Implement the ViewModel

        private val _searchResult = MutableLiveData<SearchResult>()
    val searchResult: LiveData<SearchResult> = _searchResult

    private val _resultIds = MutableLiveData<SearchResultList>()
    val resultIds: LiveData<SearchResultList> = _resultIds

    private var counter = MutableLiveData<Int>(0)

    init {
        Log.d("global", "SearchViewModel init() called")
        _searchResult.value?.title = "MyTitle"
        _searchResult.value?.objectId = 69
    }

    /*
    fun loadSearchResult() {
        viewModelScope.launch {
            try {
                val id: Int? = counter.value?.let { _resultIds.value?.objectIds?.get(it) ?: 0 }
                _searchResult.value = id?.let { searchResultRepository.getTestObject(id = it) }
                counter.value = counter.value?.inc()
            } catch (e: Exception) {
                // Retrofit error
                e.printStackTrace()
            }
        }
    }

    fun loadResultIds() {
        viewModelScope.launch {
            try {
                _resultIds.value = searchResultRepository.getResultsIds()
            } catch (e: Exception) {
                // Retrofit error
                e.printStackTrace()
            }
        }
    }
     */
}