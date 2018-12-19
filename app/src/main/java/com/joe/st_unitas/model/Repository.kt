package com.joe.st_unitas.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.joe.st_unitas.data.Image
import io.reactivex.disposables.CompositeDisposable

interface Repository {
    fun getImages(compositeDisposable: CompositeDisposable, query: String): LiveData<PagedList<Image>>
}