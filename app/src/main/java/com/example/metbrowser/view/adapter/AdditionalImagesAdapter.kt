package com.example.metbrowser.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.metbrowser.databinding.AdditionalImageBinding
import com.squareup.picasso.Picasso

class AdditionalImagesAdapter
    : ListAdapter<String, AdditionalImagesAdapter.ImageViewHolder>(ListAdapterCallback()) {

    inner class ImageViewHolder(private val binding: AdditionalImageBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(image: String) {
                // download image
                // TODO: move to coroutine / background task
                Picasso.get()
                    .load(image)
                    .into(binding.ivImage)
            }

    }

    private class ListAdapterCallback: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = AdditionalImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}