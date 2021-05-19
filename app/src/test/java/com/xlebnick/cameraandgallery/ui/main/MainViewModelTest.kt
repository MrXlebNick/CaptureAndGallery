package com.xlebnick.cameraandgallery.ui.main

import com.xlebnick.cameraandgallery.ui.gallery.FILE_1
import com.xlebnick.cameraandgallery.ui.gallery.FILE_2
import com.xlebnick.cameraandgallery.ui.gallery.FILE_3
import com.xlebnick.cameraandgallery.ui.gallery.GalleryItem
import com.xlebnick.cameraandgallery.ui.gallery.NOTES_1
import com.xlebnick.cameraandgallery.ui.gallery.NOTES_2
import com.xlebnick.cameraandgallery.ui.gallery.NOTES_3
import com.xlebnick.cameraandgallery.utils.SharedPrefsRepository
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MainViewModelTest {

    @MockK
    lateinit var sharedPrefsRepository: SharedPrefsRepository

    lateinit var SUT: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = MainViewModel(sharedPrefsRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `shouldShowGalleryButton_with files_true`() {
        // Arrange
        every { sharedPrefsRepository.getFileNamesAndNotes() } returns mapOf(
            FILE_1 to NOTES_1,
            FILE_2 to NOTES_2,
            FILE_3 to NOTES_3,
        )
        // Act
        val result = SUT.shouldShowGalleryButton()
        // Assert
        assertEquals(true, result)
    }

    @Test
    fun `shouldShowGalleryButton_without files_false`() {
        // Arrange
        every { sharedPrefsRepository.getFileNamesAndNotes() } returns mapOf()
        // Act
        val result = SUT.shouldShowGalleryButton()
        // Assert
        assertEquals(false, result)
    }

    @Test
    fun `saveNotes_calls getFileNamesAndNotes`() {
        // Arrange
        justRun { sharedPrefsRepository.saveNotes(any(), any()) }
        // Act
        SUT.saveNotes("", "")
        // Assert
        verify { sharedPrefsRepository.saveNotes(any(), any()) }
    }
}