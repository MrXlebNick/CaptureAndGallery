package com.xlebnick.cameraandgallery.ui.gallery

import com.xlebnick.cameraandgallery.utils.SharedPrefsRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
const val FILE_1 = "file:///some/file/1"
const val NOTES_1 = "some notes1"
const val FILE_2 = "file:///some/file/2"
const val NOTES_2 = "some notes2"
const val FILE_3 = "file:///some/file/3"
const val NOTES_3 = "some notes3"
class GalleryViewModelTest {

    @MockK
    lateinit var sharedPrefsRepository: SharedPrefsRepository

    lateinit var SUT: GalleryViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = GalleryViewModel(sharedPrefsRepository)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun testGetGalleryContent() {
        // Arrange
        every { sharedPrefsRepository.getFileNamesAndNotes() } returns mapOf(
            FILE_1 to NOTES_1,
            FILE_2 to NOTES_2,
            FILE_3 to NOTES_3,
        )
        // Act
        val result = SUT.getGalleryContent()
        // Assert
        assertArrayEquals(
            arrayOf(
            GalleryItem(FILE_1, NOTES_1),
            GalleryItem(FILE_2, NOTES_2),
            GalleryItem(FILE_3, NOTES_3)), result.toTypedArray())
    }

}