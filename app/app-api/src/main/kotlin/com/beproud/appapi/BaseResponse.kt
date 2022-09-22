package com.beproud.appapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class BaseResponse {
    companion object {
        fun <T> ok(data: T? = null): ResponseEntity<T> =
            ResponseEntity.ok().body(data)

        fun <T> created(data: T? = null): ResponseEntity<T> =
            ResponseEntity.status(HttpStatus.CREATED).body(data)
    }
}