package com.beproud.appapi.user.v1

import com.beproud.appapi.user.v1.dto.CreateUserRequest
import com.beproud.appapi.user.v1.dto.CreateUserResponse
import com.beproud.appapi.user.v1.dto.GetUserResponse
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import javax.transaction.Transactional

import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import java.security.Principal

private val logger = KotlinLogging.logger {}

@Service
class UserService(
    private val userDomainService: UserDomainService,
) {
    //    @Cacheable(key = "#walletAddress", value = ["creator"])
    fun getUser(authentication: Authentication): GetUserResponse {
        val userDetails = authentication.principal as UserDetails
        logger.info("getUser: ${userDetails.username}")
        val user = userDomainService.getUser(userDetails.username)
        logger.info { user }
        return GetUserResponse(user)
    }

    @Transactional
    fun createUser(request: CreateUserRequest): CreateUserResponse {
        val creator = userDomainService.createCreator(walletAddress = request.walletAddress)
        return CreateUserResponse(creator)
    }
}