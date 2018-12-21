package com.joe.st_unitas.view

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.joe.st_unitas.NETWORK_ERROR_MESSAGE
import com.joe.st_unitas.NOT_FOUND_SEARCH_RESULT_MESSAGE
import com.joe.st_unitas.api.RetrofitService
import com.joe.st_unitas.data.Image
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageKeyDataSource(
    private val retrofitService: RetrofitService,
    private val compositeDisposable: CompositeDisposable,
    private val query: String,
    private val error: MutableLiveData<String>
) : PageKeyedDataSource<Int, Image>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Image>) {
        compositeDisposable.add(
            retrofitService.getImages(size = params.requestedLoadSize, query = query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        !it.documents.isEmpty() && it.meta.isEnd
                        -> callback.onResult(it.documents, null, null)
                        !it.documents.isEmpty() && !it.meta.isEnd
                        -> callback.onResult(it.documents, null, 2)
                        it.documents.isEmpty() && it.meta.isEnd
                        -> error.value = NOT_FOUND_SEARCH_RESULT_MESSAGE
                    }
                }, {
                    it.printStackTrace()
                    error.value = NETWORK_ERROR_MESSAGE
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {
        compositeDisposable.add(
            retrofitService.getImages(
                page = params.key,
                size = params.requestedLoadSize,
                query = query
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when {
                        !it.documents.isEmpty() && it.meta.isEnd
                        -> callback.onResult(it.documents, null)
                        !it.documents.isEmpty() && !it.meta.isEnd
                        -> callback.onResult(it.documents, params.key + 1)
                        it.documents.isEmpty() && it.meta.isEnd
                        -> error.value = NOT_FOUND_SEARCH_RESULT_MESSAGE
                    }
                }, {
                    it.printStackTrace()
                    error.value = NETWORK_ERROR_MESSAGE
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Image>) {

    }
}