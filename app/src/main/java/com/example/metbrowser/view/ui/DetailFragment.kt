package com.example.metbrowser.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.metbrowser.databinding.FragmentDetailBinding
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.model.SearchResultList
import com.example.metbrowser.view.viewmodel.SearchViewModel
import com.example.metbrowser.view.viewmodel.SearchViewModel_Factory
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "DetailFragment"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: SearchViewModel by activityViewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //viewModel = ViewModelProvider(requireParentFragment()).get(SearchViewModel::class.java)

        val id = arguments?.getInt("objectId")!!
        viewModel.loadSearchResult(id)

        val item: SearchResult? = viewModel.searchResult.value


        val searchResultObserver = Observer<SearchResult> { newSearch ->
        Log.d(TAG, "objId: $id  ;  viewmodel: ${viewModel.searchResult.value?.objectId}")
            // New search result received from API
            // bind data from viewmodel to ui
            Log.d(TAG, "binding ${item?.objectId} - $id - newSearch: ${newSearch.objectId}")
            if (item != null)
                bind(item)
            else
                Log.e(TAG, "error while binding item ${id}")
        }

        viewModel.searchResult.observe(viewLifecycleOwner, searchResultObserver)
    }

    private fun bind(item: SearchResult?) {
        Log.d(TAG, "binding ${item?.objectId}")
        item?.let {
            binding.tvTitle.text = it.title
            binding.tvArtist.text = it.artist
            if ("" == it.primaryImage) {
                binding.ivPrimaryImage.setImageResource(0)
            } else {
                Picasso.get()
                    .load(it.primaryImage)
                    .into(binding.ivPrimaryImage)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}