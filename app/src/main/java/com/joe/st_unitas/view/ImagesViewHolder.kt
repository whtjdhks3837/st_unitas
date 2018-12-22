package com.joe.st_unitas.view

import android.content.Context
import android.graphics.Point
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.joe.st_unitas.R
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.databinding.SearchImageItemBinding

class ImagesViewHolder(
    private val context: Context,
    private val binding: SearchImageItemBinding,
    private val displaySize: Point
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: Image?) {
        image?.let {
            val width = displaySize.x
            val height = (image.height.toDouble() * (width.toDouble() / image.width.toDouble())).toInt()

            Glide.with(context)
                .load(image.imageUrl)
                .apply(requestOption(width, height))
                .into(binding.image)
        }
    }

    private fun requestOption(width: Int, height: Int) = RequestOptions()
        .placeholder(R.drawable.ic_crop_original)
        .override(width, height)
}