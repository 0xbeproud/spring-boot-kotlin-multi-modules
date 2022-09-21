package com.beproud.appapi.creator.v1

import com.beproud.appapi.BaseResponse
import com.beproud.appapi.creator.v1.dto.CreateCreatorRequest
import com.beproud.appapi.creator.v1.dto.CreateCreatorResponse
import com.beproud.appapi.creator.v1.dto.GetCreatorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/creators")
class CreatorController(
        private val creatorService: CreatorService
) {
    @GetMapping
    fun getGreator(
            @RequestParam(required = true, value = "walletAddress") walletAddress: String
    ): ResponseEntity<GetCreatorResponse> = BaseResponse.ok(this.creatorService.getCreator(walletAddress = walletAddress))

    @PostMapping
    fun createCreator(@Valid @RequestBody request: CreateCreatorRequest): ResponseEntity<CreateCreatorResponse> = BaseResponse.ok(this.creatorService.createCreator(request))

}