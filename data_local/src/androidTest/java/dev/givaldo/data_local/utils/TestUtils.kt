package dev.givaldo.data_local.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take

@ExperimentalCoroutinesApi
suspend fun <T> Flow<T>.test(count: Int = 1, onCollect: (T) -> Any) {
    take(count).collect {
        onCollect(it)
    }
}
