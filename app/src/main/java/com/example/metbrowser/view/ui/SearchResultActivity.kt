package com.example.metbrowser.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.metbrowser.databinding.ActivitySearchResultBinding
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.view.viewmodel.SearchResultViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {

    private val searchResultViewModel: SearchResultViewModel by viewModels()
    private lateinit var binding: ActivitySearchResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }
}