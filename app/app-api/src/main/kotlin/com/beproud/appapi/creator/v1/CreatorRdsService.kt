package com.beproud.appapi.creator.v1

import com.beproud.domain.rds.creator.Creator
import com.beproud.domain.rds.creator.CreatorRedisRepository
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class CreatorRdsService(
    private val creatorRedisRepository: CreatorRedisRepository
) {

    fun getCreator(walletAddress: String): Creator? {
        return this.creatorRedisRepository.findByWalletAddress(walletAddress)
    }

    @Transactional
    fun createCreator(walletAddress: String): Creator {
        return this.creatorRedisRepository.save(Creator.create(walletAddress = walletAddress))
    }
}