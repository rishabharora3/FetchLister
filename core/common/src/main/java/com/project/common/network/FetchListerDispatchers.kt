package com.project.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val fetchListerDispatcher: FetchListerDispatchers)

enum class FetchListerDispatchers {
    Default,
    IO,
}
