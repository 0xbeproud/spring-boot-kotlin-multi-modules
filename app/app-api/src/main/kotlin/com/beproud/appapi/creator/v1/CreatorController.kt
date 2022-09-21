package com.beproud.appapi.creator.v1

import com.beproud.appapi.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/creators")
class CreatorController(
        private val creatorService: CreatorService
) {
    @GetMapping
    fun getGreator(
            @RequestParam(required = true, value = "walletAddress") walletAddress: String
    ) = BaseResponse.ok(this.creatorService.getCreator(walletAddress = walletAddress))
}