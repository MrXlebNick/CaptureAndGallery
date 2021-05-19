package com.xlebnick.cameraandgallery.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.utils.FileUtils
import com.xlebnick.cameraandgallery.utils.SharedPrefsRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val fileUtils: FileUtils, private val sharedPrefsRepository: SharedPrefsRepository) : ViewModel() {
    fun shouldShowGalleryButton() = !fileUtils.isOutputDirectoryEmpty()
    fun saveNotes(uri: Uri, notes: String) {
        sharedPrefsRepository.saveNotes(uri.toString(), notes)
    }
}