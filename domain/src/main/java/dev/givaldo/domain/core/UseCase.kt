package dev.givaldo.domain.core

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
abstract class UseCase<T, in Params> {

    abstract suspend operator fun invoke(params: Params? = null): Flow<T>

}