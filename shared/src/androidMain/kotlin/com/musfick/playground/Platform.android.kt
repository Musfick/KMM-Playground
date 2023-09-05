package com.musfick.playground

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}


actual fun getPlatform() = module {
    viewModel {
        SampleViewModel(get())
    }
}