package com.example.metbrowser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.metbrowser.databinding.SearchResultListItemBinding
import com.example.metbrowser.view.viewmodel.SearchViewModel


class SearchResultAdapter(vm: SearchViewModel): ListAdapter<Int, SearchResultAdapter.SearchResultViewHolder>(ListAdapterCallBack()){

    private val viewModel: SearchViewModel

    init {
        viewModel = vm
    }

    class SearchResultViewHolder(private val binding:SearchResultListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(result: Int) {
            binding.tvId.text = result.toString()
            // TODO: bind click listener to go to detail screen
            binding.tvId.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = SearchResultListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = viewModel.resultIds.value?.objectIds?.get(position)
        // TODO: get item at position and insert in onBind
        if (item != null) {
            holder.onBind(item)
        }
    }

    private class ListAdapterCallBack: DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }
}
