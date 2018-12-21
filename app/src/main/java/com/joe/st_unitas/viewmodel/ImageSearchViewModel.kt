package com.joe.st_unitas.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.model.Repository
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*
import java.util.concurrent.TimeUnit
import androidx.lifecycle.Observer

class ImageSearchViewModel(private val repository: Repository) : BaseViewModel() {
    val editDispoableQueue = PriorityQueue<Disposable>()
    val editOneSecond = MutableLiveData<Any>()
    val images = MutableLiveData<PagedList<Image>>()
    val error = MutableLiveData<String>()
    val progress = MutableLiveData<Boolean>()

    fun getImages(owner: LifecycleOwner, query: String) {
        repository.getImages(compositeDisposable, query).observe(owner, Observer {
            images.value = it
        })
    }

    fun updateSearchEdit() {
        editDispoableQueue.add(
            Observable
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