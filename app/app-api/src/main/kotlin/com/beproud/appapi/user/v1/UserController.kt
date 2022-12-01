package com.beproud.appapi.user.v1

import com.beproud.appapi.BaseResponse
import com.beproud.appapi.connect.v1.dto.ConnectResponse
import com.beproud.appapi.user.v1.dto.CreateUserRequest
import com.beproud.appapi.user.v1.dto.CreateUserResponse
import com.beproud.appapi.user.v1.dto.GetUserResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
@Tag(name="User Controller")
@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "getUser", description = "getUser", tags = ["User Controller"])
    @ApiResponses(*[
        ApiResponse(responseCode = "200", description = "OK", content = [Content(schema = Schema(implementation = ConnectResponse::class))]),
    ])
    @GetMapping
    fun getUser(authentication: Authentication): ResponseEntity<GetUserResponse> =
        BaseResponse.ok(this.userService.getUser(authentication))

    @PostMapping
    fun createUser(
        @Valid @RequestBody request: CreateUserRequest
    ): ResponseEntity<CreateUserResponse> = BaseResponse.ok(this.userService.createUser(request))
}