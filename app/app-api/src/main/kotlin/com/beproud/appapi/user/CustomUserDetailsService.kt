package com.beproud.appapi.user

import com.beproud.appapi.user.v1.UserDomainService
import mu.KotlinLogging
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


private val logger = KotlinLogging.logger {}

@Service
class CustomUserDetailsService(
    private val userDomainService: UserDomainService
) : UserDetailsService {
    override fun loadUserByUsername(walletAddress: String): UserDetails {
        logger.info { "loadUserByUsername -> walletAddress: $walletAddress" }
        val user = userDomainService.getUser(walletAddress) ?: throw Exception("not exist user")
        return UserPrincipal.create(user)
    }

    fun loadUserById(id: Long): UserDetails {
        val user = userDomainService.findById(id) ?: throw UsernameNotFoundException("User not found with id: $id")
        return UserPrincipal.create(user)
    }
}