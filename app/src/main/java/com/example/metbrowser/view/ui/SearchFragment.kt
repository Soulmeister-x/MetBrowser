package com.example.metbrowser.view.ui

import android.os.Bundle
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.metbrowser.R
import com.example.metbrowser.databinding.FragmentSearchBinding
import com.example.metbrowser.view.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "SearchFragment"

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSearchResultTitle.text = "Willkommen beim Test"

        binding.btSearch.setOnClickListener {
            this.findNavController().navigate(R.id.action_searchFragment_to_detailFragment)
        }

        /*
        // Setup button to fetch new SearchResult
        binding.btSearch.setOnClickListener {
            searchResultViewModel.loadSearchResult()
        }

        // Setup view model observer to populate view upon response
        val searchResultObserver = Observer<SearchResult> { newSearchResult ->
            // New search result received from API
            binding.tvSearchResultTitle.text = "${newSearchResult.objectId}: ${newSearchResult.title}"
            binding.tvSearchArtist.text = newSearchResult.artist

            if ("".equals(newSearchResult.primaryImage)) {
                binding.ivPrimaryImage.setImageResource(0)
            }
            else {
                lifecycleScope.launch {
                    Picasso.get()
                        .load(newSearchResult.primaryImage)
                        .into(binding.ivPrimaryImage)
                }
            }
        }
        searchResultViewModel.searchResult.observe(this, searchResultObserver)

        // load data at start
        searchResultViewModel.loadResultIds()
         */
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}