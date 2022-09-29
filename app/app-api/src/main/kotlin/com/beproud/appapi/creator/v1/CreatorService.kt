package com.beproud.appapi.creator.v1

import com.beproud.appapi.creator.v1.dto.CreateCreatorRequest
import com.beproud.appapi.creator.v1.dto.CreateCreatorResponse
import com.beproud.appapi.creator.v1.dto.GetCreatorResponse
import mu.KotlinLogging
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import javax.transaction.Transactional

private val logger = KotlinLogging.logger {}

@Service
class CreatorService(
    private val creatorDomainService: CreatorDomainService,
) {

    @Cacheable(key = "#walletAddress", value = ["creator"])
    fun getCreator(walletAddress: String): GetCreatorResponse {
        logger.info { "walletAddress: $walletAddress" }
        TimeUnit.SECONDS.sleep(3)
        val creator = creatorDomainService.getCreator(walletAddress)
        logger.info { creator }
        return GetCreatorResponse(creator)
    }

    @Transactional
    fun createCreator(request: CreateCreatorRequest): CreateCreatorResponse {
        val creator = creatorDomainService.createCreator(walletAddress = request.walletAddress)
        return CreateCreatorResponse(creator)
    }
}