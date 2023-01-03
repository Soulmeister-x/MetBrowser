package com.example.metbrowser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.metbrowser.R
import com.example.metbrowser.databinding.FragmentSearchBinding
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.view.viewmodel.SearchResultViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchResultViewModel: SearchResultViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Setup button to fetch new SearchResult
        binding.btSearch.setOnClickListener {
            searchResultViewModel.loadResultIds(binding.etSearchInput.toString())
        }

        // Setup view model observer to populate view upon response
        val searchResultObserver = Observer<SearchResult> { newSearchResult ->
            // New search result received from API
            // TODO: populate textViews with strings and placeholders
            // TODO: populate recyclerview with ids

        }

        // TODO: set on click listener to recyclerview Entries -> navigate to detail screen

        searchResultViewModel.searchResult.observe(viewLifecycleOwner, searchResultObserver)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}