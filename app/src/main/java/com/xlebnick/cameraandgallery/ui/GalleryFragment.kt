package com.xlebnick.cameraandgallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.xlebnick.cameraandgallery.R
import com.xlebnick.cameraandgallery.ui.base.BaseFragment

class GalleryFragment : BaseFragment() {

    private val viewModel: GalleryViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }
}