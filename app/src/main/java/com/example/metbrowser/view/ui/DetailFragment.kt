package com.example.metbrowser.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.metbrowser.R
import com.example.metbrowser.databinding.FragmentDetailBinding
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.view.adapter.AdditionalImagesAdapter
import com.example.metbrowser.view.viewmodel.SearchViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "DetailFragment"

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: SearchViewModel by activityViewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterx: AdditionalImagesAdapter

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
        adapterx = AdditionalImagesAdapter()

        // init recyclerView for additional images
        binding.rvAdditionalImages.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = adapterx
        }

        // New search result received from API
        val searchResultObserver = Observer<SearchResult> { newSearch ->
            // get current item
            val item: SearchResult = viewModel.searchResult.value!!

            /*
            Log.d(TAG, "id" +
                    "\n(bundle)   : $id;" +
                    "\n(viewmodel): ${viewModel.searchResult.value?.objectId};" +
                    "\n(item)     : ${item.objectId};")
            */

            // bind data from viewmodel to ui
            bind(item)
        }

        viewModel.searchResult.observe(viewLifecycleOwner, searchResultObserver)

        // TODO: should the Request be sent here or in SearchFragment
        val id = arguments?.getInt("objectId")!!
        viewModel.loadSearchResult(id)
    }

    private fun bind(item: SearchResult?) {
        Log.d(TAG, "binding ${item.toString()}")
        item?.let {
            if (it.additionalImages.isNotEmpty()) {
                adapterx.submitList(it.additionalImages)
            } else {
                binding.rvAdditionalImages.visibility = RecyclerView.INVISIBLE
            }
            binding.tvTitle.text = "${it.objectId}: ${it.title}"
            binding.tvArtist.text = it.artist
            if ("" == it.primaryImage) {
                // load placeholder image, bc image is not available
                binding.ivPrimaryImage.setImageResource(R.drawable.ic_image_not_available)
            } else {
                // load image
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