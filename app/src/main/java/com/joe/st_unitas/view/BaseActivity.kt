package com.joe.st_unitas.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.joe.st_unitas.viewmodel.ImageSearchViewModel
import com.joe.st_unitas.viewmodel.ImageSearchViewModelFactory
import org.koin.android.ext.android.inject

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewDataBinding: T
    abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }
}