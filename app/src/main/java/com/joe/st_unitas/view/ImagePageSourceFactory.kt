package com.joe.st_unitas.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import io.reactivex.disposables.CompositeDisposable

class ImagePageSourceFactory(
    private val retrofitService: RetrofitService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String
) : DataSource.Factory<Int, Image>() {

    //private val sourceLiveData = MutableLiveData<ImageKeyDataSource>()

    override fun create(): DataSource<Int, Image> {
        //sourceLiveData.postValue(source)
        return ImageKeyDataSource(retrofitService, compositeDisposable, query)
    }
}