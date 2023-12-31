package com.musfick.playground

import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin {
    modules(
        module {
            single {
                SampleUseCase()
            }
        },
        getPlatform()
    )
}