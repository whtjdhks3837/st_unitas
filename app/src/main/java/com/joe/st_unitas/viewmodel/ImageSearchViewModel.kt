package com.joe.st_unitas.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.model.Repository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ImageSearchViewModel(private val repository: Repository) : BaseViewModel() {
    val editOneSecondAfter = MutableLiveData<Any>()
    val images = MutableLiveData<PagedList<Image>>()
    val error = MutableLiveData<String>()
    val progress = MutableLiveData<Boolean>()
    val aa: LiveData<Boolean>
        get() = progress

    fun getImages(owner: LifecycleOwner, query: String) {
        repository.getImages(compositeDisposable, query, progress, error).observe(owner, Observer {
            images.value = it
        })
    }

    fun updateSearchEdit() {
        compositeDisposable.clear()
        addDisposable(
            Observable
                .timer(1000L, TimeUnit.MILLISECONDS)
                .subscribe({
                    editOneSecondAfter.postValue("")
                }, {
                    it.printStackTrace()
                    error.value = "알 수 없는 오류입니다."
                })
        )
    }
}