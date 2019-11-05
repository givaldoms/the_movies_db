package dev.givaldo.presentation.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect


@FlowPreview
fun <T> Flow<T>.asLiveData(): LiveData<Result<T>> = liveData {
    try {
        collect {
            emit(Result.success(it))
        }
    } catch (e: Exception) {
        emit(Result.failure(e))
    }
}

@FlowPreview
fun <T> Flow<T>.asMutableLiveData(): MutableLiveData<Result<T>> = liveData {
    try {
        collect {
            emit(Result.success(it))
        }
    } catch (e: Exception) {
        emit(Result.failure(e))
    }
} as MutableLiveData<Result<T>>

fun <T> Result<T>.handle(onFailure: (Throwable) -> Unit = {}, onSuccess: (T) -> Unit) {
    val success = getOrNull()

    if (success != null) {
        onSuccess(success)
        return
    }

    val failure = exceptionOrNull()
    if (failure != null) {
        onFailure(failure)
    }

}