package com.joe.st_unitas.model

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.view.ImagePageSourceFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class NetworkRepository(private val retrofitService: RetrofitService) : Repository {
    override fun getImages(compositeDisposable: CompositeDisposable, query: String): LiveData<PagedList<Image>> {
        val sourceFactory = ImagePageSourceFactory(retrofitService, compositeDisposable, query)
        return LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder()
                .setInitialLoadSizeHint(15)
                .setPageSize(15)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(true)
                .build()
        ).build()
    }
}