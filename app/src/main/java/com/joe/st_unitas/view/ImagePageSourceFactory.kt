package com.joe.st_unitas.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import io.reactivex.disposables.CompositeDisposable

class ImagePageSourceFactory(
    private val retrofitService: RetrofitService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String,
    private val error: MutableLiveData<String>
) : DataSource.Factory<Int, Image>() {

    override fun create(): DataSource<Int, Image> =
        ImageKeyDataSource(retrofitService, compositeDisposable, query, error)
}