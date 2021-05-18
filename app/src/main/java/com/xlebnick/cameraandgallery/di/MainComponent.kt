package com.xlebnick.cameraandgallery.di

import android.content.Context
import com.xlebnick.cameraandgallery.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentBindingModule::class,
        ActivityBindingModule::class
    ]
)
@Singleton
interface MainComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder
        fun build(): MainComponent
    }

    fun inject(graphLeaf: App)

}