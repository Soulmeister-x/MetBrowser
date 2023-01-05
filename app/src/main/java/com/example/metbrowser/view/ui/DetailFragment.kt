package com.example.metbrowser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.metbrowser.databinding.FragmentDetailBinding
import com.example.metbrowser.model.SearchResult
import com.example.metbrowser.view.viewmodel.SearchViewModel
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
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind data from viewmodel to ui
        bind(viewModel.searchResult.value)

    }

    private fun bind(item: SearchResult?) {
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