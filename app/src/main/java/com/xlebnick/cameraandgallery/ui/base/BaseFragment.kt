package com.xlebnick.cameraandgallery.ui.base

import android.content.Context
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

open class BaseFragment: DaggerFragment() {
    @CallSuper
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}
