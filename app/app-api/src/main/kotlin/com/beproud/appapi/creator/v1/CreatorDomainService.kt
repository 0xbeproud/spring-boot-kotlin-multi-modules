package com.beproud.appapi.creator.v1

import com.beproud.domain.rds.creator.Creator
import com.beproud.domain.rds.creator.CreatorRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class CreatorDomainService(
    private val creatorRepository: CreatorRepository
) {

    fun getCreator(walletAddress: String): Creator? {
        return this.creatorRepository.findByWalletAddress(walletAddress)
    }

    @Transactional
    fun createCreator(walletAddress: String): Creator {
        return this.creatorRepository.save(Creator.create(walletAddress = walletAddress))
    }
}