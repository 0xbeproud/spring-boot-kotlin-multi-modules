package com.beproud.appapi.connect.v1

import com.beproud.appapi.BaseResponse
import com.beproud.appapi.connect.v1.dto.ConnectRequest
import com.beproud.appapi.connect.v1.dto.ConnectResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name="Connect Controller")
@RestController
@RequestMapping("/api/v1/connect")
class ConnectController(
    private val service: ConnectService
) {
    @Operation(summary = "Connect", description = "connect", tags = ["Connect Controller"])
    @ApiResponses(*[
        ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = ConnectResponse::class))]),
    ])
    @PostMapping
    fun connect(
        @Valid @RequestBody request: ConnectRequest
    ): ResponseEntity<ConnectResponse> = BaseResponse.ok(service.connect(request))
}