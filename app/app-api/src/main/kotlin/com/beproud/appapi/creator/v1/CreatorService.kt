package com.beproud.appapi.creator.v1

import com.beproud.appapi.creator.v1.dto.CreatorResponse
import com.beproud.core.util.wallet.WalletUtils
import com.beproud.domain.rds.creator.CreatorRepository
import org.springframework.stereotype.Service

@Service
class CreatorService(
        private val creatorRepository: CreatorRepository
) {

    fun getCreator(walletAddress: String): CreatorResponse {

        return CreatorResponse(walletType = WalletUtils.getWalletType(), walletAddress = walletAddress)
    }
}