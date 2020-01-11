package dev.givaldo.presentation.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
fun <T> Flow<T>.asLiveData(): LiveData<Result<T>> = liveData {
    try {
        collect {
            emit(Result.success(it))
        }
    } catch (e: Exception) {
        emit(Result.failure(e))
    }
}


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