package com.beproud.config

import com.beproud.appapi.ApiException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorResponse(val message: String)

@RestControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException) = ResponseEntity.status(ex.status).body(ErrorResponse(ex.message!!))

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse(ex.message!!))
}