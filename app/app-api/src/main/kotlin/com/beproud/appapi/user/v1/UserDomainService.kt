package com.beproud.appapi.user.v1

import com.beproud.appapi.connect.v1.dto.ConnectRequest
import com.beproud.domain.rds.user.User
import com.beproud.domain.rds.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
class UserDomainService(
    private val userRepository: UserRepository
) {

    fun findById(id: Long): User? {
        return this.userRepository.findByIdOrNull(id)
    }

    fun getUser(walletAddress: String): User? {
        return this.userRepository.findByWalletAddress(walletAddress)
    }

    @Transactional
    fun createCreator(walletAddress: String): User {
        return this.userRepository.save(User.create(walletAddress = walletAddress))
    }

    fun createIfNotExist(request: ConnectRequest): User {
        return userRepository.findByWalletAddress(request.walletAddress) ?:
            userRepository.save(User.create(walletAddress = request.walletAddress))
    }
}