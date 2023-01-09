package com.example.metbrowser.view.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metbrowser.R
import com.example.metbrowser.databinding.FragmentSearchBinding
import com.example.metbrowser.model.SearchResultList
import com.example.metbrowser.view.adapter.SearchResultAdapter
import com.example.metbrowser.view.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SearchFragment"

@AndroidEntryPoint
class SearchFragment : Fragment(), SearchResultAdapter.ListItemClickListener {

    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterx: SearchResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterx = SearchResultAdapter( this )

        // init adapter
        binding.rvOutputObjIds.apply {
            adapter = adapterx
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btSearch.setOnClickListener {
            val searchTerm = binding.etSearchInput.text.toString()
            viewModel.loadResultIds( searchTerm )
            closeKeyboard()
        }

        val searchResultObserver = Observer<SearchResultList> { newSearch ->
            // New search result received from API
            adapterx.submitList( newSearch.objectIds )
        }
        viewModel.resultIds.observe(viewLifecycleOwner, searchResultObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun closeKeyboard() {
        val manager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken,0)
    }

    override fun onItemClicked(objectId: Int, position: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(objectId = objectId)
        findNavController().navigate(action)
    }


}