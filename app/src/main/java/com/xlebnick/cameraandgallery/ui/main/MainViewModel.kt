package com.xlebnick.cameraandgallery.ui.main

import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.utils.FileUtils
import javax.inject.Inject

class MainViewModel @Inject constructor(private val fileUtils: FileUtils) : ViewModel() {
    fun shouldShowGalleryButton() = !fileUtils.isOutputDirectoryEmpty()
}