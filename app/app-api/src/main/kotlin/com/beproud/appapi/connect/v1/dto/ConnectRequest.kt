package com.beproud.appapi.connect.v1.dto

import javax.validation.constraints.NotNull

data class ConnectRequest(
    @field:NotNull(message = "지갑 주소는 필수 입니다.")
    val walletAddress: String,
)