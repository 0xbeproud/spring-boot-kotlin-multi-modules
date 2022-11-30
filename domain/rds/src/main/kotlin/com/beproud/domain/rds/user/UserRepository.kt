package com.beproud.domain.rds.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByWalletAddress(walletAddress: String): User?
}