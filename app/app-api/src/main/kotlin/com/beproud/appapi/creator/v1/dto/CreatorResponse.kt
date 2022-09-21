package com.beproud.appapi.creator.v1.dto

import com.beproud.core.type.wallet.WalletType

data class CreatorResponse(val walletType: WalletType, val walletAddress: String)