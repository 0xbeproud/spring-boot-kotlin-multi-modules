package com.beproud.appapi.user.v1

import com.beproud.appapi.BaseResponse
import com.beproud.appapi.user.v1.dto.CreateUserRequest
import com.beproud.appapi.user.v1.dto.CreateUserResponse
import com.beproud.appapi.user.v1.dto.GetUserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUser(
        @RequestParam(required = true, value = "walletAddress") walletAddress: String
    ): ResponseEntity<GetUserResponse> =
        BaseResponse.ok(this.userService.getUser(walletAddress = walletAddress))

    @PostMapping
    fun createUser(
        @Valid @RequestBody request: CreateUserRequest
    ): ResponseEntity<CreateUserResponse> = BaseResponse.ok(this.userService.createUser(request))
}