package com.joe.st_unitas.view

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.data.ImageResponse
import com.joe.st_unitas.kakaoToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ImageKeyDataSource(
    private val retrofitService: RetrofitService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String
) : PageKeyedDataSource<Int, Image>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Image>) {
        Log.e("tag", "loadInitial")
        Log.e("tag", "${params.requestedLoadSize}, $query")
        compositeDisposable.add(
            retrofitService.getImages(page = 1, size = params.requestedLoadSize, query = query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.documents, 0, params.requestedLoadSize)
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        Log.e("tag", "loadBefore")
        Log.e("tag", "${params.key}, ${params.requestedLoadSize}, $query")
        compositeDisposable.add(
            retrofitService.getImages(
                page = params.key,
                size = params.requestedLoadSize,
                query = query
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onResult(it.documents, params.key + params.requestedLoadSize)
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        Log.e("tag", "loadBefore")
    }

}