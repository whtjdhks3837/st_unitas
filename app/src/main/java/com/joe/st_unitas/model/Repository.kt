package com.joe.st_unitas.model

import com.joe.st_unitas.data.Image
import io.reactivex.Single

interface Repository {
    fun getImages(query: String): Single<List<Image>>
}