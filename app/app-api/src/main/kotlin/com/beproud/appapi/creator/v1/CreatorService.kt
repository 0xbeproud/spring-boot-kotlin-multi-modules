package com.beproud.appapi.creator.v1

import com.beproud.appapi.creator.v1.dto.CreateCreatorRequest
import com.beproud.appapi.creator.v1.dto.CreateCreatorResponse
import com.beproud.appapi.creator.v1.dto.GetCreatorResponse
import com.beproud.core.util.wallet.WalletUtils
import com.beproud.domain.rds.creator.Creator
import com.beproud.domain.rds.creator.CreatorRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import javax.transaction.Transactional

private val logger = KotlinLogging.logger {}

@Service
class CreatorService(private val creatorRepository: CreatorRepository) {

    fun getCreator(walletAddress: String): GetCreatorResponse {
        return GetCreatorResponse(walletType = WalletUtils.getWalletType(), walletAddress = walletAddress)
    }

    @Transactional
    fun createCreator(request: CreateCreatorRequest): CreateCreatorResponse {
        val creator = this.creatorRepository.save(Creator.create(walletAddress = request.walletAddress))
        return CreateCreatorResponse(creator)
    }
}