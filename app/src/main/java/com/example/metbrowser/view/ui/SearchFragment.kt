package com.example.metbrowser.view.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metbrowser.databinding.FragmentSearchBinding
import com.example.metbrowser.model.SearchResultList
import com.example.metbrowser.view.adapter.SearchResultAdapter
import com.example.metbrowser.view.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SearchFragment"

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by activityViewModels()

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

        adapterx = SearchResultAdapter(viewModel)

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
            Log.d(TAG, "x list submitted: ${newSearch.total} results")
        }
        viewModel.resultIds.observe(viewLifecycleOwner, searchResultObserver)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun closeKeyboard() {
        val manager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(activity?.currentFocus?.windowToken,0)
    }


}