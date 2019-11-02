package dev.givaldo.data_remote.utils

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

@FlowPreview
suspend fun <D> requestWrapper(
    call: suspend () -> D
): Flow<D> {
    return flow {
        try {
            emit(call())
        } catch (httpException: HttpException) {
            throw httpException.parseError()
        } catch (ioException: IOException) {
            throw InternetErrorException()
        } catch (stateException: IllegalStateException) {
            throw ServerErrorException()
        }
    }
}


fun HttpException.parseError(): Throwable {
//    val error = response().errorBody()?.string()?.let { it.parseJson<HashMap<String, String>>(it) }
    return DataSourceException(
        message = ErrorMessageEnum.GENERIC_ERROR.value
    )
}