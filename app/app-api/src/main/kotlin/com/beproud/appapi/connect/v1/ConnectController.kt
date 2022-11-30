package com.beproud.appapi.connect.v1

import com.beproud.appapi.BaseResponse
import com.beproud.appapi.connect.v1.dto.ConnectRequest
import com.beproud.appapi.connect.v1.dto.ConnectResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/connect")
class ConnectController(
    private val service: ConnectService
) {
    @PostMapping
    fun connect(
        @Valid @RequestBody request: ConnectRequest
    ): ResponseEntity<ConnectResponse> = BaseResponse.ok(service.connect(request))
}