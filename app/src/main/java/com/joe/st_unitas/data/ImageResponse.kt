package com.joe.st_unitas.data

import androidx.paging.PagedList
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("documents") @Expose val documents: List<Image>
)

data class Image(
    @SerializedName("image_url") @Expose val imageUrl: String
)