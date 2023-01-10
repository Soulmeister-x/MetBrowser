package com.example.metbrowser.ui.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metbrowser.databinding.FragmentSearchBinding
import com.example.metbrowser.ui.adapter.SearchResultAdapter
import com.example.metbrowser.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
            closeKeyboard()
            Log.d(TAG, "button clicked ($searchTerm)")
            viewModel.onSearchTextChanged(searchTerm)
        }

        lifecycle.coroutineScope.launch {
            viewModel.result.collect {
                adapterx.submitList(it.objectIds)
            }
        }
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