package com.example.metbrowser.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.metbrowser.R
import com.example.metbrowser.databinding.FragmentDetailBinding
import com.example.metbrowser.view.viewmodel.SearchResultViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailFragment: Fragment(R.layout.fragment_detail) {

    private val searchResultViewModel: SearchResultViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        searchResultViewModel.searchResult.value?.let {
            binding.tvSearchResultTitle.text = it.title

            binding.tvSearchArtist.text = it.artist

            if ("".equals(it.primaryImage)) {
                binding.ivPrimaryImage.setImageResource(0)
            }
            else {
                lifecycleScope.launch {
                    Picasso.get()
                        .load(it.primaryImage)
                        .into(binding.ivPrimaryImage)
                }
            // TODO: populate recyclerview
            // TODO: create recyclerview adapter
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}