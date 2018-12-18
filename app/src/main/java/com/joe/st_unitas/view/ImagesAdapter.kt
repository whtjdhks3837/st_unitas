package com.joe.st_unitas.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.databinding.SearchImageItemBinding.*

class ImagesAdapter(private val context: Context) : PagedListAdapter<Image, ImagesViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.imageUrl == newItem.imageUrl

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.imageUrl == newItem.imageUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(context, binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}