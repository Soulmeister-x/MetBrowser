package com.example.metbrowser.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.metbrowser.R
import com.example.metbrowser.databinding.FragmentDetailBinding
import com.example.metbrowser.view.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val searchResultViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = DetailFragment()
    }

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
        binding.tvTest.text = "HELLO TEST"

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}