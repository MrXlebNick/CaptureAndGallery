package com.xlebnick.cameraandgallery.ui.base

import android.content.Context
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xlebnick.cameraandgallery.utils.FragmentNavControllerHelper
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navControllerHelper: FragmentNavControllerHelper

    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        navControllerHelper.setNavController(findNavController())
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        navControllerHelper.setNavController(null)
    }

}
