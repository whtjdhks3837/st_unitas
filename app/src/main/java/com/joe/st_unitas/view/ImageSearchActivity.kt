package com.joe.st_unitas.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.joe.st_unitas.R
import com.joe.st_unitas.data.Image
import com.joe.st_unitas.databinding.ActivityImageSearchBinding
import com.joe.st_unitas.viewmodel.ImageSearchViewModel
import com.joe.st_unitas.viewmodel.ImageSearchViewModelFactory
import org.koin.android.ext.android.inject

class ImageSearchActivity : BaseActivity<ActivityImageSearchBinding>() {

    override val layoutResourceId: Int = R.layout.activity_image_search
    private val imageSearchViewModelFactory: ImageSearchViewModelFactory by inject()
    private val viewModel: ImageSearchViewModel by lazy {
        ViewModelProviders.of(this, imageSearchViewModelFactory)
            .get(ImageSearchViewModel::class.java)
    }
    private val imagesAdapter: ImagesAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.listView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = imagesAdapter
        }

        viewDataBinding.searchEdit.addTextChangedListener(textWatcher)

        viewModel.editOneSecond.observe(this, Observer {
            if (viewDataBinding.searchEdit.text.toString() != "") {
                viewModel.progress.value = true
                viewModel.getImages(viewDataBinding.searchEdit.text.toString())
            }
        })

        viewModel.images.observe(this, Observer { images ->
            viewModel.progress.value = false
            // TODO : List to PageList
            imagesAdapter.submitList(images as PagedList<Image>)
        })

        viewModel.error.observe(this, Observer { msg ->
            viewModel.progress.value = false
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        })

        viewDataBinding.viewmodel = viewModel
        viewDataBinding.setLifecycleOwner(this)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            viewModel.editDispoableQueue.forEach {
                it.dispose()
            }
            viewModel.editDispoableQueue.clear()
            viewModel.updateSearchEdit()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}
