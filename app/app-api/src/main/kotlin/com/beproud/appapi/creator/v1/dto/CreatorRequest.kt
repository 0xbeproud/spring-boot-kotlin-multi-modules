package com.beproud.appapi.creator.v1.dto

import javax.validation.constraints.NotNull

data class CreateCreatorRequest(
        @field:NotNull(message = "지갑 주소는 필수 입니다.")
        val walletAddress: String,
)