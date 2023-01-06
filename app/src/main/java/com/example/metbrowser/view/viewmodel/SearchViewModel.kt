package com.example.metbrowser.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList
import com.example.metbrowser.service.repository.SearchResultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SearchViewModel"

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchResultRepository: SearchResultRepository
): ViewModel() {
    private val _searchResult = MutableLiveData<SearchResult>()
    val searchResult: LiveData<SearchResult> = _searchResult

    private val _resultIds = MutableLiveData<SearchResultList>()
    val resultIds: LiveData<SearchResultList> = _resultIds

    fun loadSearchResult(id: Int) {
        viewModelScope.launch {
            try {
                _searchResult.value = searchResultRepository.getObject(id)
                val v2 = searchResult.value
                Log.d(TAG, "binding detail: ${v2.toString()}")
            } catch (e: Exception) {
                // Retrofit error
                e.printStackTrace()
            }
        }
    }

    fun loadResultIds(search: String) {
        viewModelScope.launch {
            try {
                _resultIds.value = searchResultRepository.getResultsIds(search)
            } catch (e: Exception) {
                // Retrofit error
                e.printStackTrace()
            }
        }
    }

}