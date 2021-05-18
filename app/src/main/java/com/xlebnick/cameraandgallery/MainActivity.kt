package com.xlebnick.cameraandgallery

import android.os.Bundle
import androidx.navigation.findNavController
import com.xlebnick.cameraandgallery.utils.ActivityNavControllerHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navControllerHelper: ActivityNavControllerHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        navControllerHelper.setNavController(findNavController(R.id.navHostFragment))
    }
}