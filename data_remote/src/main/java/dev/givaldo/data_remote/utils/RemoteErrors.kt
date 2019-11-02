package dev.givaldo.data_remote.utils

enum class ErrorMessageEnum(val value: String) {
    INTERNET_ERROR("Sem conexão com a Internet."),
    GENERIC_ERROR("Tivemos um problema de conexão, tente novamente mais tarde")
}

class DataSourceException(message: String = ErrorMessageEnum.GENERIC_ERROR.value) : Exception(message)
class ServerErrorException(message: String = ErrorMessageEnum.GENERIC_ERROR.value) : Exception(message)
class InternetErrorException(message: String = ErrorMessageEnum.INTERNET_ERROR.value) : Exception(message)