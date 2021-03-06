package com.joe.st_unitas.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.view.ImagePageSourceFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class NetworkRepository(private val retrofitService: RetrofitService) : Repository {
    override fun getImages(
        compositeDisposable: CompositeDisposable,
        query: String,
        progress: MutableLiveData<Boolean>,
        error: MutableLiveData<String>
    ): LiveData<PagedList<Image>> {
        val sourceFactory = ImagePageSourceFactory(retrofitService, compositeDisposable, query, progress, error)
        return LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder()
                .setInitialLoadSizeHint(50)
                .setPageSize(50)
                .setPrefetchDistance(1)
                .setEnablePlaceholders(false)
                .build()
        ).build()
    }
}