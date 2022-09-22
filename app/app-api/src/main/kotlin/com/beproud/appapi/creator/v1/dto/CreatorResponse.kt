package com.beproud.appapi.creator.v1.dto

import com.beproud.core.type.wallet.WalletType
import com.beproud.domain.rds.creator.Creator

data class GetCreatorResponse(val walletType: WalletType, val walletAddress: String)

data class CreateCreatorResponse(val creator: Creator)
