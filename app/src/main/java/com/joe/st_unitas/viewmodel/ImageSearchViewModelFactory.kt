package com.joe.st_unitas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joe.st_unitas.model.Repository

@Suppress("UNCHECKED_CAST")
class ImageSearchViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageSearchViewModel(repository) as T
    }
}