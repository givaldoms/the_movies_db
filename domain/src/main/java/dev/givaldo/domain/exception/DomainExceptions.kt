package dev.givaldo.domain.exception

open class DomainException(message: String, title: String? = null) :
    RuntimeException(message, RuntimeException(title))

sealed class ParamException(message: String, title: String? = null) :
    DomainException(message, title)

class MissingParamsException : ParamException("Params must not be null.")
class EmptyFieldException : ParamException("Campo obrigatório.")
class FieldValueException(message: String) : ParamException(message)




