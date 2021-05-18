package com.xlebnick.cameraandgallery.di.module

import androidx.lifecycle.ViewModel
import com.xlebnick.cameraandgallery.di.ViewModelKey
import com.xlebnick.cameraandgallery.ui.GalleryViewModel
import com.xlebnick.cameraandgallery.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    abstract fun galleryViewModel(viewModel: GalleryViewModel): ViewModel
}