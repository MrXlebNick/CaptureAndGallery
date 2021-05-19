package com.xlebnick.cameraandgallery.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsRepository @Inject constructor(context: Context) {

    companion object {
        private const val SAVED_FILES_STRING_SET = "savedFiles"
        private const val NAME = "CaptureAndGallery"
    }

    private val sharedPreferences =
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    /**
     * Adds the uri to the list of saved files; adds notes for this uri
     */
    fun saveNotes(uri: String, notes: String) {
        val savedFiles = sharedPreferences.getStringSet(SAVED_FILES_STRING_SET, setOf())?.toMutableSet() ?: mutableSetOf()
        savedFiles.add(uri)

        sharedPreferences.edit()
            .putStringSet(SAVED_FILES_STRING_SET, savedFiles)
            .putString(uri, notes)
            .apply()
    }

    /**
     * Retrieves saved notes
     * @return map, where key is the uri of the file and the value is the notes value for it (can be null)
     */
    fun getNotes(): Map<String, String?> {
        val savedFiles = sharedPreferences.getStringSet(SAVED_FILES_STRING_SET, setOf()) ?: setOf()
        return savedFiles.map { it to sharedPreferences.getString(it, null)  }.toMap()
    }
}
