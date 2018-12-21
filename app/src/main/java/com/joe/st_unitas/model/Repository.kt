package com.joe.st_unitas.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.joe.st_unitas.data.Image
import io.reactivex.disposables.CompositeDisposable

interface Repository {
    fun getImages(compositeDisposable: CompositeDisposable, query: String, error: MutableLiveData<String>): LiveData<PagedList<Image>>
}