package com.xlebnick.cameraandgallery.ui

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.utils.FileUtils
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val fileUtils: FileUtils) : ViewModel() {
    fun getGalleryContent(): List<GalleryItem> {
        return fileUtils.getOutputDirectory().listFiles()?.map { GalleryItem(it.toUri(), "") } ?: listOf()
    }
}