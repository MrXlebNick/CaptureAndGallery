package com.xlebnick.cameraandgallery.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.utils.SharedPrefsRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(private val sharedPrefsRepository: SharedPrefsRepository) :
    ViewModel() {
    fun shouldShowGalleryButton() = sharedPrefsRepository.getFileNamesAndNotes().isNotEmpty()
    fun saveNotes(uri: String, notes: String) {
        sharedPrefsRepository.saveNotes(uri, notes)
    }
}