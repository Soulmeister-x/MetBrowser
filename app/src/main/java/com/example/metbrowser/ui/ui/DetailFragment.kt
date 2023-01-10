package com.example.metbrowser.ui.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.metbrowser.R
import com.example.metbrowser.databinding.FragmentDetailBinding
import com.example.metbrowser.functionality.entities.ObjectDetails
import com.example.metbrowser.ui.adapter.AdditionalImagesAdapter
import com.example.metbrowser.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "DetailFragment"

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {


    private val viewModel: DetailsViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterx: AdditionalImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        // get current item
        lifecycle.coroutineScope.launch {
            viewModel.objectDetails.collect() {
                bind(it)
            }
        }
    }

    private fun bind(item: ObjectDetails) {
        Log.d(TAG, "binding ${item.toString()}")
        item.let {
            if (it.additionalImages.isNotEmpty()) {
                adapterx.submitList(it.additionalImages)
            }
            binding.tvTitle.text = "${it.objectId}: ${it.title}"
            binding.tvArtist.text = it.artist
            if ("" == it.primaryImage) {
                Glide.with(binding.root).clear(binding.idPrimaryImage.ivImage)
                // load placeholder image, image is not available
                binding.idPrimaryImage.ivImage.setImageResource(R.drawable.ic_image_not_available)
            } else {
                // load image
                Glide.with(binding.root)
                    .load(it.primaryImage)
                    .into(binding.idPrimaryImage.ivImage)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}