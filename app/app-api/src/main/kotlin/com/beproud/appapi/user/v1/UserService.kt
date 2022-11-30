package com.beproud.appapi.user.v1

import com.beproud.appapi.user.v1.dto.CreateUserRequest
import com.beproud.appapi.user.v1.dto.CreateUserResponse
import com.beproud.appapi.user.v1.dto.GetUserResponse
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import javax.transaction.Transactional

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

@Service
class UserService(
    private val userDomainService: UserDomainService,
) {
    //    @Cacheable(key = "#walletAddress", value = ["creator"])
    fun getUser(walletAddress: String): GetUserResponse {
        logger.info { "walletAddress: $walletAddress" }
        TimeUnit.SECONDS.sleep(3)
        val user = userDomainService.getUser(walletAddress)
        logger.info { user }
        return GetUserResponse(user)
    }

    @Transactional
    fun createUser(request: CreateUserRequest): CreateUserResponse {
        val creator = userDomainService.createCreator(walletAddress = request.walletAddress)
        return CreateUserResponse(creator)
    }
}