package com.beproud.appapi.user.v1.dto

import javax.validation.constraints.NotNull

data class CreateUserRequest(
    @field:NotNull(message = "지갑 주소는 필수 입니다.")
    val walletAddress: String,
)

data class Request(
    val hello: String
)