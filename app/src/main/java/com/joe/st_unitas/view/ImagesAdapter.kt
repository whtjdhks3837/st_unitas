package com.joe.st_unitas.view

import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.databinding.SearchImageItemBinding.*

class ImagesAdapter(private val context: Context, private val displaySize: Point) :
    PagedListAdapter<Image, ImagesViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(context, binding, displaySize)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        Log.e("tag", "onBindViewHolder $position")
        holder.bind(getItem(position))
    }
}