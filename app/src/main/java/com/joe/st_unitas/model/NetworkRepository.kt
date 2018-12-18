package com.joe.st_unitas.model

import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import io.reactivex.Single

class NetworkRepository(private val retrofitService: RetrofitService) : Repository {
    override fun getImages(query: String): Single<List<Image>> {
        return retrofitService.getImages(query = query)
            .map { it -> it.documents }
    }
}