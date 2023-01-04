package com.example.metbrowser.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.example.metbrowser.R
import com.example.metbrowser.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: set search fragment
        // binding.mainFragmentHolder = SearchFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}