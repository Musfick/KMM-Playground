package com.musfick.playground
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

actual fun getPlatform() = module {
    single{
        SampleViewModel(get())
    }
}
object GetViewModels: KoinComponent {
    fun getSampleViewModel() = get<SampleViewModel>()
}