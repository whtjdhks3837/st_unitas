package com.joe.st_unitas.di

import com.joe.st_unitas.model.NetworkRepository
import com.joe.st_unitas.model.Repository
import com.joe.st_unitas.viewmodel.ImageSearchViewModelFactory
import org.koin.dsl.module.module

val imageSearchModule = module {
    factory {
        NetworkRepository(get()) as Repository
    }

    factory {
        ImageSearchViewModelFactory(get())
    }
}