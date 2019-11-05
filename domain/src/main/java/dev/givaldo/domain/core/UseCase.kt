package dev.givaldo.domain.core

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@FlowPreview
abstract class UseCase<T, in Params> {

    abstract fun run(params: Params?): Flow<T>

    operator fun invoke(params: Params? = null): Flow<T> {
        return run(params).distinctUntilChanged()
    }

}