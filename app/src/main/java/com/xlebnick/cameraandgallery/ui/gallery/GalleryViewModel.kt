package com.xlebnick.cameraandgallery.ui.gallery

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.utils.SharedPrefsRepository
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {
    fun getGalleryContent(): List<GalleryItem> {
        return sharedPrefsRepository.getNotes()
            .entries
            .asSequence() // for calculation purposes
            .map { GalleryItem(Uri.parse(it.key), it.value ?: "") } // map with notes if found
            .toList() // get back to the list
    }
}