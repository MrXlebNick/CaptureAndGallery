package com.xlebnick.cameraandgallery.ui.gallery

import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.utils.SharedPrefsRepository
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {
    fun getGalleryContent(): List<GalleryItem> {
        return sharedPrefsRepository.getFileNamesAndNotes()
            .entries
            .asSequence() // for calculation purposes
            .map { GalleryItem(it.key, it.value ?: "") } // map with notes if found
            .toList() // get back to the list
    }
}