package com.example.metbrowser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.metbrowser.databinding.SearchResultListItemBinding


class SearchResultAdapter(private val listItemClickListener: ListItemClickListener)
    : ListAdapter<Int, SearchResultAdapter.SearchResultViewHolder>(ListAdapterCallBack()){

    interface ListItemClickListener {
        fun onItemClicked(objectId: Int, position: Int)
    }

    private class ListAdapterCallBack: DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = SearchResultListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        // get item at position and insert in onBind
        holder.bind(getItem(position))
    }


    inner class SearchResultViewHolder(private val binding:SearchResultListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(objectId: Int) {
            binding.tvId.apply {
                text = objectId.toString()
                // bind click listener to go to detail screen
                setOnClickListener {
                    listItemClickListener.onItemClicked(objectId, adapterPosition)
                }
            }
        }
    }


}
