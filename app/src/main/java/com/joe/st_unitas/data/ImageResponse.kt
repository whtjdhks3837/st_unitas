package com.joe.st_unitas.data

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("documents") val documents: List<Image>,
    @SerializedName("meta") val meta: Meta
)

data class Image(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int
)

data class Meta(
    @SerializedName("is_end") val isEnd: Boolean
)