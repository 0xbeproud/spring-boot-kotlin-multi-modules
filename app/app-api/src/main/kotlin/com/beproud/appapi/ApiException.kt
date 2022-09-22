package com.beproud.appapi

import org.springframework.http.HttpStatus

open class ApiException(val status: HttpStatus, message: String) : RuntimeException(message)

// InternalServerException
open class InternalServerException(message: String) : ApiException(HttpStatus.INTERNAL_SERVER_ERROR, message)
class SomethingWrongException(message: String) : InternalServerException(message)

// EntityNotFoundException
open class EntityNotFoundException(message: String) : ApiException(HttpStatus.BAD_REQUEST, message)

// EntityAlreadyExistException
open class EntityExistException(message: String) : ApiException(HttpStatus.CONFLICT, message)