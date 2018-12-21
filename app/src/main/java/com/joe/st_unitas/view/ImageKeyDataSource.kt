package com.joe.st_unitas.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageKeyDataSource(
    private val retrofitService: RetrofitService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String
) : PageKeyedDataSource<Int, Image>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Image>) {
        compositeDisposable.add(
            retrofitService.getImages(size = params.requestedLoadSize, query = query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.documents, null, 2)
                }, {
                    it.printStackTrace()
                })
        )
        Log.e("tag", "loadInitial")
        Log.e("tag", "${params.requestedLoadSize}, $query")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        Log.e("tag", "loadAfter")
        Log.e("tag", "${params.key}, ${params.requestedLoadSize}, $query")
        compositeDisposable.add(
            retrofitService.getImages(
                page = params.key,
                size = params.requestedLoadSize,
                query = query
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.documents, params.key + 1)
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {

    }
}