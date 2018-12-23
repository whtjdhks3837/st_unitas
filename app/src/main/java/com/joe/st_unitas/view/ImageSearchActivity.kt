package com.joe.st_unitas.view

import android.graphics.Point
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.st_unitas.R
import com.joe.st_unitas.databinding.ActivityImageSearchBinding
import com.joe.st_unitas.viewmodel.ImageSearchViewModel
import com.joe.st_unitas.viewmodel.ImageSearchViewModelFactory
import org.koin.android.ext.android.get

class ImageSearchActivity : BaseActivity<ActivityImageSearchBinding>() {

    override val layoutResourceId: Int = R.layout.activity_image_search
    private val viewModel: ImageSearchViewModel by lazy {
        ViewModelProviders.of(this, ImageSearchViewModelFactory(get()))
            .get(ImageSearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imagesAdapter = ImagesAdapter(this, getDisplaySize())
        viewDataBinding.listView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imagesAdapter
        }

        viewDataBinding.searchEdit.addTextChangedListener(textWatcher)

        viewModel.editOneSecondAfter.observe(this, Observer {
            imagesAdapter.submitList(null)
            if (viewDataBinding.searchEdit.text.toString() != "") {
                viewModel.getImages(this, viewDataBinding.searchEdit.text.toString())
            }
        })

        viewModel.images.observe(this, Observer { images ->
            imagesAdapter.submitList(images)
        })

        viewModel.error.observe(this, Observer { msg ->
            viewModel.progress.value = false
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })

        viewDataBinding.viewmodel = viewModel
        viewDataBinding.setLifecycleOwner(this)
    }

    private fun getDisplaySize(): Point {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            viewModel.updateSearchEdit()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}
