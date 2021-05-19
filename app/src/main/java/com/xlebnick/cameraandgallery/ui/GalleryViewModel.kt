package com.xlebnick.cameraandgallery.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.utils.FileUtils
import com.xlebnick.cameraandgallery.utils.SharedPrefsRepository
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val fileUtils: FileUtils,
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {
    fun getGalleryContent(): List<GalleryItem> {
        val savedNotes = sharedPrefsRepository.getNotes()
        return fileUtils
            .getOutputDirectory()
            .listFiles() // get files in the directory
            ?.asSequence() // for calculation purposes
            ?.map { Uri.fromFile(it) } // get Uri from file
            ?.map { GalleryItem(it, savedNotes.getOrElse(it.toString()) {""} ?: "") } // map with notes if found
            ?.toList() // get back to the list
            ?: listOf() // fallback to empty list
    }
}