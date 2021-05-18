package com.xlebnick.cameraandgallery.utils

import android.content.Context
import androidx.camera.core.ImageCapture
import com.xlebnick.cameraandgallery.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class FileUtils @Inject constructor(private val context: Context) {

    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }

    private var outputDirectory: File = getOutputDirectory()

    @Suppress("DEPRECATION")
    fun getOutputDirectory(): File {
        val mediaDir = context.externalMediaDirs?.firstOrNull()?.let {
            File(it, context.resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            return mediaDir
        else
            context.filesDir
    }

    fun createImageCaptureOptions(): ImageCapture.OutputFileOptions {
        // Create time-stamped output file to hold the image
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )
        return ImageCapture.OutputFileOptions.Builder(photoFile).build()
    }

    fun isOutputDirectoryEmpty(): Boolean = getOutputDirectory().listFiles().isNullOrEmpty()
}