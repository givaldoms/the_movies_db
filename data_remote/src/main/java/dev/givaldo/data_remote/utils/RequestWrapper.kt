package dev.givaldo.data_remote.utils

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

fun <D> flowApi(
    call: suspend () -> D
) = flow {
    try {
        emit(call())
    } catch (httpException: HttpException) {
        println("Request error [HttpException] -> ${httpException.message}")
        throw httpException.parseError()
    } catch (ioException: IOException) {
        println("Request error [IllegalStateException] -> ${ioException.message}")
        throw InternetErrorException()
    } catch (stateException: IllegalStateException) {
        println("Request error [IllegalStateException] -> ${stateException.message}")
        throw ServerErrorException()
    }
}


fun HttpException.parseError(): Throwable {
//    val error = response().errorBody()?.string()?.let { it.parseJson<HashMap<String, String>>(it) }
    return DataSourceException(
        message = ErrorMessageEnum.GENERIC_ERROR.value
    )
}