package com.xlebnick.cameraandgallery.di

import com.xlebnick.cameraandgallery.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment
}