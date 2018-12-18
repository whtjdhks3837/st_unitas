package com.joe.st_unitas.view

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.databinding.SearchImageItemBinding

class ImagesViewHolder(private val context: Context, private val binding: SearchImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image?) {
        image?.let {
            Glide.with(context)
                .load(image.imageUrl)
                .into(binding.image)
        }
    }
}