package com.joe.st_unitas.viewmodel

import androidx.lifecycle.MutableLiveData
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.model.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class ImageSearchViewModel(private val repository: Repository) : BaseViewModel() {
    val editDispoableQueue = PriorityQueue<Disposable>()
    val editOneSecond = MutableLiveData<Any>()
    val images = MutableLiveData<List<Image>>()
    val error = MutableLiveData<String>()
    val progress = MutableLiveData<Boolean>()

    fun getImages(query: String) {
        addDisposable(repository.getImages(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (!it.isEmpty()) {
                    images.value = it
                } else {
                    error.value = "검색 결과가 없습니다."
                }
            }, {
                it.printStackTrace()
                error.value = "이미지를 불러올 수 없습니다."
            })
        )
    }

    fun updateSearchEdit() {
        editDispoableQueue.add(Observable
            .timer(1000L, TimeUnit.MILLISECONDS)
            .subscribe({
                editOneSecond.postValue("")
            }, {
                it.printStackTrace()
            })
        )
        addDisposable(editDispoableQueue.last())
    }
}